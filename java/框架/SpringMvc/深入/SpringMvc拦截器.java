拦截器:
相当于struts2中的过滤器

步骤:
1.拦截器配置
2.需要实现 HandlerInterceptor 接口 
3.执行顺序
4.实现登录认证



-----------------------------------------
1.拦截器配置
 在springmvc中配置
 	<!--拦截器 -->
<mvc:interceptors>
	<!--多个拦截器,顺序执行 -->
	<!-- 登陆认证拦截器 -->
	<mvc:interceptor>
		<mvc:mapping path="/**"/>
		<bean class="cn.itcast.ssm.interceptor.LoginInterceptor"></bean>
	</mvc:interceptor>
	<mvc:interceptor>
		<!-- 表示所有url包括子url路径 -->
		<mvc:mapping path="/**"/>
		<bean class="cn.itcast.ssm.interceptor.HandlerInterceptor1"></bean>
	</mvc:interceptor>
	<mvc:interceptor>
		<mvc:mapping path="/**"/>
		<bean class="cn.itcast.ssm.interceptor.HandlerInterceptor2"></bean>
	</mvc:interceptor>
</mvc:interceptors>


----------------------------------------
2.需要实现 HandlerInterceptor 接口 
此接口中包含了三个方法
public class HandlerInterceptor1 implements HandlerInterceptor {
	//进入 Handler方法之前执行
	//用于身份认证、身份授权
	//比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("HandlerInterceptor1...preHandle");
		
		//return false表示拦截，不向下执行
		//return true表示放行
		return true;
	}

	//进入Handler方法之后，返回modelAndView之前执行
	//应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里传到视图，也可以在这里统一指定视图
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("HandlerInterceptor1...postHandle");
		
	}

	//执行Handler完成执行此方法
	//应用场景：统一异常处理，统一日志处理
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		System.out.println("HandlerInterceptor1...afterCompletion");
	}

}
--------------------------------
3.执行顺序

3.1.两个拦截器都放行
总结：
preHandle方法按顺序执行，
postHandle和afterCompletion按拦截器配置的逆向顺序执行。

3.2.拦截器1放行，拦截器2不放行
总结：
拦截器1放行，拦截器2 preHandle才会执行。
拦截器2 preHandle不放行，拦截器2 postHandle和afterCompletion不会执行。
只要有一个拦截器不放行，postHandle不会执行。

3.3.拦截器1不放行，拦截器2不放行
拦截器1 preHandle不放行，postHandle和afterCompletion不会执行。
拦截器1 preHandle不放行，拦截器2不执行。

小结：
根据测试结果，对拦截器应用。

比如：统一日志处理拦截器，需要该拦截器preHandle一定要放行，且将它放在拦截器链接中第一个位置。

比如：登陆认证拦截器，放在拦截器链接中第一个位置。权限校验拦截器，放在登陆认证拦截器之后。（因为登陆通过后才校验权限）
------------------------------------------------
4.实现登录认证
拦截器进行拦截校验
	如果请求的url是公开地址（无需登陆即可访问的url），让放行
	如果用户session 不存在跳转到登陆页面
	如果用户session存在放行，继续操作


@Controller
public class LoginController {

	// 登陆
	@RequestMapping("/login")
	public String login(HttpSession session, String username, String password)
			throws Exception {

		// 调用service进行用户身份验证
		// ...

		// 在session中保存用户身份信息
		session.setAttribute("username", username);
		// 重定向到商品列表页面
		return "redirect:/items/queryItems.action";
	}

	// 退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {

		// 清除session
		session.invalidate();

		// 重定向到商品列表页面
		return "redirect:/items/queryItems.action";
	}

}

拦截器部分：
public class LoginInterceptor implements HandlerInterceptor {
	//进入 Handler方法之前执行
	//用于身份认证、身份授权
	//比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//获取请求的url
		String url = request.getRequestURI();
		//判断url是否是公开 地址（实际使用时将公开 地址配置配置文件中）
		//这里公开地址是登陆提交的地址
		if(url.indexOf("login.action")>=0){
			//如果进行登陆提交，放行
			return true;
		}
		
		//判断session
		HttpSession session  = request.getSession();
		//从session中取出用户身份信息
		String username = (String) session.getAttribute("username");
		
		if(username != null){
			//身份存在，放行
			return true;
		}
		
		//执行这里表示用户身份需要认证，跳转登陆页面
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		
		//return false表示拦截，不向下执行
		//return true表示放行
		return false;
	}

	//进入Handler方法之后，返回modelAndView之前执行
	//应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里传到视图，也可以在这里统一指定视图
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("HandlerInterceptor1...postHandle");
		
	}

	//执行Handler完成执行此方法
	//应用场景：统一异常处理，统一日志处理
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		System.out.println("HandlerInterceptor1...afterCompletion");
	}

}
