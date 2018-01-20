springmvc参数解析器原理
自定义参数解析器


----------------------------
原理：
Spring 调用action方法的过程，先通过参数解析器。
所以action方法中，带的入参，才能接收到页面的值。

如下：session,year,yycgdQueryVo等参数都是需要经过参数解析器才能
获取到页面数据的。
// 创建采购单基本信息保存方法
@RequestMapping("/addcgdsubmit")
public @ResponseBody
SubmitResultInfo addcgdsubmit(HttpSession session, String year,
	YycgdQueryVo yycgdQueryVo) throws Exception {}


页面key/value值 --> 参数解析器 --> 属性编辑器 --> 调用Action方法形参。

参数解析器：
通过反射读取action方法的形参（名称和类型）

springmvc自带了很多参数解析器，基本可以满足需求。
也可以自定义参数解析器。
------------------------------

自定义参数解析器:
1.编写自定义参数解析类
2.配置自定义参数解析器

1.编写自定义参数解析类：
需要实现 WebArgumentResolver
其他的参数绑定过程也是需要经过这一步骤的。

例子：
想
public class UserArgumentResolver implements WebArgumentResolver {

	//methodParameter存储action方法的参数信息
	//webRequest是springmvc封装对象，通过此对象获取request和session等域的值
	@Override
	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) throws Exception {
		
		//如果参数的类型是activeUser从session中取当前用户信息设置到action方法形参上
		if(methodParameter.getParameterType().equals(ActiveUser.class)){
			
			//从session中取当前用户信息
			ActiveUser activeUser = (ActiveUser) webRequest.getAttribute(Config.ACTIVEUSER_KEY, WebRequest.SCOPE_SESSION);
			
			return activeUser;//将参数值向action的方法形参赋值
		}
		
		return UNRESOLVED;
	}

}
Action:
编写之后，就可以在action的形参中直接指定 ActiveUser 并能获得页面值。
// 采购单列表维护结果集,json
@RequestMapping("/yycgdlist_result")
public @ResponseBody
DataGridResultInfo yycgdlist_result(// HttpSession session,
		ActiveUser activeUser, String year,// 年份
		YycgdQueryVo yycgdQueryVo,// 查询条件
		int page, int rows) throws Exception {
	// 当前用户
	/*
	 * ActiveUser activeUser = (ActiveUser) session
	 * .getAttribute(Config.ACTIVEUSER_KEY);
	 */
	// 用户所属的单位(医院单位id)
	String useryyid = activeUser.getSysid();// 单位id
	// 列表的总数
	int total = cgdService.findYycgdCount(useryyid, year, yycgdQueryVo);
	// 分页参数
	PageQuery pageQuery = new PageQuery();
	pageQuery.setPageParams(total, rows, page);
	yycgdQueryVo.setPageQuery(pageQuery);// 设置分页参数
	// 分页查询列表
	List<YycgdCustom> list = cgdService.findYycgdList(useryyid, year,
			yycgdQueryVo);
	DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
	dataGridResultInfo.setTotal(total);
	dataGridResultInfo.setRows(list);
	return dataGridResultInfo;
}



2.配置自定义参数解析器
在springmvc.xml中加上映射器适配器配置中加上
	<mvc:argument-resolvers>  
           <bean class="yycg.base.action.converters.UserArgumentResolver"/>  
    </mvc:argument-resolvers>
如下 ：
<mvc:annotation-driven conversion-service="conversionService">
	<mvc:argument-resolvers>  
           <bean class="yycg.base.action.converters.UserArgumentResolver"/>  
    </mvc:argument-resolvers> 
</mvc:annotation-driven>