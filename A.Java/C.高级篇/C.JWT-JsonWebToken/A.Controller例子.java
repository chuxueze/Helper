@Controller
public class TokenLoginController extends BaseController {

	@Autowired
	private UserService userService;
	@Resource
	private JwtUtils jwtUtils;
	
	@Autowired
	private CacheRedis<?, ?> cacheRedis;

	@Autowired
	private UserDetailService userDetailService;

	@Value("${tokenOverMillis}")
	private Long tokenOverMillis;

	@Value("${tokenCache}")
	private String tokenCache;

	@Value("${tokenCacheOverTime}")
	private String tokenCacheOverTime;
	
	@Value("${userDetailCache}")
	private String userDetailCache;

	/**
	 * 基于token登陆
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tokenLogin")
	@ResponseBody
	public HttpEntity testLogin(HttpServletRequest request, HttpServletResponse response) {
		String userAccount = request.getParameter("userAccount");
		String password = request.getParameter("userPassword");
		try {
			User user = userService.qryUserByUsername(userAccount);
			new UsernamePasswordAuthenticationToken(userAccount, password);
			return new HttpEntity(HttpStatus.OK, true, "请求成功",  getToken(user));
		} catch (AuthenticationException ex) {
			return new HttpEntity(HttpStatus.OK, false, "请求成功", "用户名密码错误！");
		} catch (Exception e) {
			//e.printStackTrace();
			return new HttpEntity(HttpStatus.OK, false, "请求成功", "缓存token出错！");
		}

	}

	/**
	 * @author lengzhijun
	 * @Description: 根据token查询账号的详细信息,并将信息缓存到redis中
	 */
	@ResponseBody
	@RequestMapping(value = { "/token/qryUserDetail" }, method = RequestMethod.POST)
	public HttpEntity qryUserDetail(@RequestBody Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			User user = UserUtils.getUser();
			UserDetail userDetail = null;
			if (user.getUserId() != null && StringUtils.isNotEmpty(user.getUsername())) {
				userDetail=(UserDetail) cacheRedis.getObject(userDetailCache, user.getUsername());
				if(userDetail==null) {
					userDetail = userDetailService.qryUserDetail(user.getUserId());
					cacheRedis.setObject(userDetailCache, userDetail.getUserName(), userDetail);
				}
			}
			return new HttpEntity(HttpStatus.OK, true, "请求成功", userDetail);
		} catch (Exception e) {
			return new HttpEntity(HttpStatus.OK, false, "请求成功", "获取权限信息出错！");
		}

	}

	/**
	 * 获取token,如果存在，且在有效期内则复用，否则重新生成
	 * @author lengzhijun
	 * @param user
	 * @return
	 * @throws Exception
	 */
	private String getToken(User user) throws Exception {
		Long userId = user.getUserId();
		String userAccount = user.getUsername();
		String userPassword = user.getUserPassword();
		String redisToken = (String) cacheRedis.getObject(tokenCache, userAccount);
		Long overTime = cacheRedis.getObject(tokenCacheOverTime, userAccount)==null?0L:(Long) cacheRedis.getObject(tokenCacheOverTime, userAccount);
		long currentTimeMillis = System.currentTimeMillis();
		if (redisToken != null && overTime > currentTimeMillis) {
			// 延长token过期时间
			cacheRedis.setObject(tokenCacheOverTime, user.getUsername(), System.currentTimeMillis() + tokenOverMillis);
			return redisToken;
		} else {
			UserDetails userDetails = new JwtUserDetails(userId, userAccount,userPassword, null);
			redisToken = jwtUtils.generateAccessToken(userDetails);
			cacheRedis.setObject(tokenCache, userAccount, redisToken);
			cacheRedis.setObject(tokenCacheOverTime, userAccount,currentTimeMillis+tokenOverMillis);
			return redisToken;
		}

	}

}



/**
 * 获取当前用户
 * @return 取不到返回 new User()
 */
public static User getUser(){
	RequestAttributes ra = RequestContextHolder.getRequestAttributes();
	User user=new User();
	if(ra!=null) {
		HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
		String token = request.getHeader("token");
		JwtUserDetails vo = jwtUtils.getUserFromToken(token);
		if (vo!=null && StringUtils.isNotEmpty(String.valueOf(vo.getUserId()))){
			//User user = userService.qryUserById(vo.getUserId());
			user.setUserId(vo.getUserId());
			user.setUsername(vo.getUsername());
		}
	}
	// 如果没有登录，则返回实例化空的User对象。
	return user;
}