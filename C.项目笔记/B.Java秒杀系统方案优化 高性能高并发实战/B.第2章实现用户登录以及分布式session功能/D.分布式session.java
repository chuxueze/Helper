1.思路:
2.实例代码:
3.将相关数据校验写入解析器中 UserArgumentResolver:





----------------------------------------------------
1.思路:
1.1.将 toekn 相关的数据放在第三方的缓存中，如 Redis 。
1.2.思想：
第1次登录成功时，随机生成1个 token，将 token 作为 Redis 中的
key，存放生成 token 时设置的 User 对象。


-----------------------------
2.实例代码:
2.1:
登录校验账号密码通过之后，生成 token，以 token 为 key，将用户对象的信息
放到 Redis 中，同时放到 cookie 返回给前端。
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;
    private static final String COOKIE_NAME_TOKEN = "token";
    @RequestMapping("checkMsg")
    public Result checkMsg(HttpServletResponse response, @Valid @RequestBody User userParam){

        //相关参数校验省略
        User user = userService.getUserByAccount(userParam.getAccount());
        if(user == null){
            return Result.error(CodeMsg.ACCOUNT_EMPTY);
        }
        //数据库存在的密码与盐
        String dbPassword = user.getPassword();
        String salt = user.getSalt();
        //前端一次md5加密加固定盐之后的密码
        String passwordParam = userParam.getPassword();
        //前端传递过来的密码，加上数据库的随机盐，再与已经保存的密码比较。如果是一致的，则证明密码正确。
        String password = MD5Util.formPassToDBPass(passwordParam,salt);
        if(!password.equals(dbPassword)){
            return Result.error(CodeMsg.PASSWORD_EROOR);
        }
        //生成token
        String token = UUIDUtil.uuid();
            redisService.set(MiaoShaUserKey.token,token,user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(MiaoShaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);

        return Result.success(CodeMsg.LOGIN_SUCCESS);
    }
}

---------
2.2
  可以做1个拦截器，当访问请求路径之前，先拦截并校验 token 信息的准确性。
确保 token 中的信息能够取到用户数据，再放行拦截器。
 	@RequestMapping("checkToken")
    @ResponseBody
    public Result checkToken(HttpServletResponse response, @CookieValue(value = COOKIE_NAME_TOKEN,required = false) String cookieToken, @RequestParam(value=COOKIE_NAME_TOKEN,required = false) String paramToken){
        if(StringUtils.isBlank(cookieToken) && StringUtils.isBlank(paramToken)){
            return Result.error( CodeMsg.PARAMETER_ERROR);
        }
        String token = StringUtils.isBlank(paramToken)?cookieToken:paramToken;
        MiaoShaUser miaoShaUser = redisService.get(MiaoShaUserKey.token,token,MiaoShaUser.class);
        
        if(miaoShaUser != null){
            // 延长有效期，重新设置cookie
            Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
            cookie.setMaxAge(MiaoShaUserKey.token.expireSeconds());
            cookie.setPath("/");
            response.addCookie(cookie);

            return Result.success(CodeMsg.LOGIN_SUCCESS);
        }
        return Result.error(CodeMsg.PARAMETER_ERROR);
    }

-----------------------------

3.将相关数据校验写入解析器中 UserArgumentResolver:
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    UserArgumentResolver userArgumentResolver;
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }
}


@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String COOKIE_NAME_TOKEN = "token";
    @Autowired
    RedisService redisService;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz = parameter.getParameterType();
        return clazz == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);

        String paramToken = request.getParameter(COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(request,COOKIE_NAME_TOKEN);
        if(StringUtils.isBlank(cookieToken) && StringUtils.isBlank(paramToken)){
            return Result.error( CodeMsg.PARAMETER_ERROR);
        }
        String token = StringUtils.isBlank(paramToken)?cookieToken:paramToken;
        MiaoShaUser miaoShaUser = redisService.get(MiaoShaUserKey.token,token,MiaoShaUser.class);
        if(miaoShaUser != null){
            // 延长有效期，重新设置cookie
            Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
            cookie.setMaxAge(MiaoShaUserKey.token.expireSeconds());
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return null;
    }

    private String getCookieValue(HttpServletRequest request,String cookieName){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(cookieName)){
                return cookie.getValue();
            }
        }
        return null;
    }
}