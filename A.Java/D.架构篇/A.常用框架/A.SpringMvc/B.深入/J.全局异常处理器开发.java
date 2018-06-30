异常处理:
	前端控制器 DispatcherServlet 中，有进行异常的捕获与处理。

Dao 如果有异常，抛出异常到 Service，
Service 如果有异常，抛出异常到 Controller,
Controller 如果有异常，抛出异常到 DispatcherServlet,
DispatcherServlet 通过springmvc中的 异常处理器（ExceptionResolver）统一异常处理。
调用全局异常处理器进行异常处理。

一个系统，只有一个统一异常处理器。

步骤：
1.自定义异常类:
2.全局异常处理器:
3.在springmvc.xml中配置注入全局异常处理器:

1.自定义异常类：
对不同的异常定义异常处理类，继承 Exception。
/**
 * 
 * <p>Title: CustomException</p>
 * <p>Description:系统 自定义异常类，针对预期的异常，需要在程序中抛出此类的异常 </p>
 * <p>Company: www.itcast.com</p> 
 * @author	传智.燕青
 * @date	2015-4-14上午11:52:02
 * @version 1.0
 */
public class CustomException extends Exception {
	private static final long serialVersionUID = 1L;
	//异常信息
	public String message;
	
	public CustomException(String message){
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
----------------------------------------------------------
2.全局异常处理器:
思路：
	系统遇到异常，在程序中手动抛出，dao抛给service、service给controller、controller抛给前端控制器，前端控制器调用全局异常处理器。
	全局异常处理器处理思路：
		解析出异常类型
		如果该 异常类型是系统 自定义的异常，直接取出异常信息，在错误页面展示
		如果该 异常类型不是系统 自定义的异常，构造一个自定义的异常类型（信息为“未知错误”）

springmvc提供一个HandlerExceptionResolver接口
resolveException()方法 Exception ex 就是前端控制器抛出的异常。

 ex instanceof CustomException: ex 是否属于 CustomException 的一个实例

public class CustomExceptionResolver implements HandlerExceptionResolver {
 
	/**
	 * （非 Javadoc）
	 * <p>Title: resolveException</p>
	 * <p>Description: </p>
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex 系统 抛出的异常
	 * @return
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		//handler就是处理器适配器要执行Handler对象（只有method）
		
//		解析出异常类型
//		如果该 异常类型是系统 自定义的异常，直接取出异常信息，在错误页面展示
//		String message = null;
//		if(ex instanceof CustomException){
//			message = ((CustomException)ex).getMessage();
//		}else{
////			如果该 异常类型不是系统 自定义的异常，构造一个自定义的异常类型（信息为“未知错误”）
//			message="未知错误";
//		}
		
		//上边代码变为
		CustomException customException = null;
		if(ex instanceof CustomException){//如果ex属于CustomException的一个类对象
			customException = (CustomException)ex;
		}else{
			customException = new CustomException("未知错误");
		}
		
		//错误信息
		String message = customException.getMessage();
		
		
		ModelAndView modelAndView = new ModelAndView();
		
		//将错误信息传到页面
		modelAndView.addObject("message", message);
		
		//指向错误页面
		modelAndView.setViewName("error"); 

		
		return modelAndView;
	}

}
-----------------------------
3.在springmvc.xml中配置注入全局异常处理器:

<!-- 全局异常处理器
只要实现HandlerExceptionResolver接口就是全局异常处理器
 -->
<bean class="cn.itcast.ssm.exception.CustomExceptionResolver"></bean>