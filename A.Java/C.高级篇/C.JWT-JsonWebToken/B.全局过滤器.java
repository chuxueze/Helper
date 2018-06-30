
/**
 * 使用 token 进行身份验证的过滤器。 如果 request header 中有 auth-token，使用 auth-token
 * 的值查询对应的登陆用户，如果用户有效则放行访问，否则返回 401 错误。
 */
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private static ThreadLocal<Boolean> allowSessionCreation = new ThreadLocal<>(); // 是否允许当前请求创建 session
	@Resource
	private JwtUtils jwtUtils;
	@Autowired
	private CacheRedis<?, ?> cacheRedis;

	@Value("${tokenOverMillis}")
	private Long tokenOverMillis;

	@Value("${jwt.header}")
	private String tokenHeader;

	@Value("${tokenCache}")
	private String tokenCache;

	@Value("${tokenCacheOverTime}")
	private String tokenCacheOverTime;

	private static final JsonUtils jsonUtils = JsonUtils.getInstance();

	public TokenAuthenticationFilter() {
		super(new AntPathRequestMatcher("/tokenLogin", "POST")); // 参考 UsernamePasswordAuthenticationFilter
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		try {
			String token = request.getHeader(tokenHeader);
			String username = jwtUtils.getUsernameFromToken(token);
			UserDetails userDetails = jwtUtils.getUserFromToken(token);
			if (StringUtils.isEmpty(username)) {
				return null;
			} else {
				String redisToken = (String) cacheRedis.getObject(tokenCache, username);
				Long overTime = (Long) cacheRedis.getObject(tokenCacheOverTime, username);
				long currentTimeMillis = System.currentTimeMillis();
				if (!token.equals(redisToken) || overTime < currentTimeMillis) {
					return null;
				}
				// 延长token过期时间
				cacheRedis.setObject(tokenCache, username, token);
				cacheRedis.setObject(tokenCacheOverTime, username, System.currentTimeMillis() + tokenOverMillis);
			}
			return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
					userDetails.getAuthorities());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		Authentication auth = null;
		// 默认创建 session
		allowSessionCreation.set(true);
		// 如果 header 里有 auth-token 时，则使用 token 查询用户数据进行登陆验证
		if (request.getHeader(tokenHeader) != null) {
			// 1. 尝试进行身份认证
			// 2. 如果用户无效，则返回 401
			// 3. 如果用户有效，则保存到 SecurityContext 中，供本次方式后续使用
			auth = attemptAuthentication(request, response);
			if (auth == null) {
				this.resultResponse(response, new HttpEntity(HttpStatus.OK, false, "token无效，请重新登陆获取token！", "401"));
				return;
			}
			// 保存认证信息到 SecurityContext，禁止 HttpSessionSecurityContextRepository 创建 session
			allowSessionCreation.set(false);
			SecurityContextHolder.getContext().setAuthentication(auth);
		} else {
			this.resultResponse(response, new HttpEntity(HttpStatus.OK, false, "token不存在！", "401"));
			return;
		}
		// 继续调用下一个 filter: UsernamePasswordAuthenticationToken
		chain.doFilter(request, response);
	}

	public static boolean isAllowSessionCreation() {
		return allowSessionCreation.get();
	}

	public void resultResponse(HttpServletResponse response, HttpEntity httpEntity) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			setResponseHeader(response);
			PrintWriter out = null;
			String obj = jsonUtils.objectToJson(httpEntity);
			out = response.getWriter();
			out.print(obj);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//允许跨域访问
	public void setResponseHeader(HttpServletResponse httpServletResponse) {
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token");
		httpServletResponse.setHeader("Access-Control-Expose-Headers", "*");
	}
}