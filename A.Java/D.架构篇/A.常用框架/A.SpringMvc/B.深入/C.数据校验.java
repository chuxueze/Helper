服务端数据校验：
对于安全性较高的，建议在服务端进行校验。
在服务端校验的好处:
1.安全性提高
2.不管客户端是什么类型，都要经过服务端的校验（不管是 B/S,C/S,浏览器，手机，远程接口）

控制层controller:
    校验页面请求的参数的合法性。
业务层service:（使用较多）
	主要校验关键业务参数，仅限于service接口中使用的参数
持久层dao:
	一般是不校验的

一：普通校验
二：分组校验
--------------------------
一：普通校验
springMvc校验使用hibernate的校验框架（和hibernate没有关系）
思路：
	页面提交请求参数，请求到controller方法，使用validation进行校验。
如果校验出错，将错误信息展示到页面中。

步骤：
1.引入校验框架的三个jar包
2.配置校验器
3.将校验器注入到处理器适配器中
4.在pojo中添加校验规则
5.在controller中捕获校验信息
6.在页面中显示数据

2.在springmvc.xml中配置校验器:
<!-- 校验器 -->
<bean id="validator"
	class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean">
	<!-- hibernate校验器-->
	<property name="providerClass" value="org.hibernate.validator.HibernateValidator" />
	<!-- 指定校验使用的资源文件，在文件中配置校验错误信息，如果不指定则默认使用classpath下的ValidationMessages.properties -->
	<property name="validationMessageSource" ref="messageSource" />
</bean>
<!-- 校验错误信息配置文件 -->
<bean id="messageSource"
	class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
	<!-- 资源文件名-->
	<property name="basenames">   
      	 <list>    
           <value>classpath:CustomValidationMessages</value> 
      	 </list>   
   	</property>
	<!-- 资源文件编码格式 -->
	<property name="fileEncodings" value="utf-8" />
	<!-- 对资源文件内容缓存时间，单位秒 -->
	<property name="cacheSeconds" value="120" />
</bean>

3. 在处理器适配器中，增加validator,指定校验器的id
<mvc:annotation-driven conversion-service="conversionService"
	validator="validator"></mvc:annotation-driven>

4.
如校验名称在 1~30 个字符之间
//校验名称在1到30字符中间
//message是提示校验出错显示的信息
//groups：此校验属于哪个分组，groups可以定义多个分组
@Size(min=1,max=30,message="{items.name.length.error}",groups={ValidGroup1.class})
private String name;
//非空校验
@NotNull(message="{items.createtime.isNUll}")
private Date createtime;


指定之后需要在 CustomValidationMessages.properties 文件中配置
items.name.length.error=请输入1~30位字符
items.createtime.isNUll=请输入商品的生产日期

在controller中捕获校验信息
// 商品信息修改提交
// 在需要校验的pojo前边添加@Validated，在需要校验的pojo后边添加BindingResult
// bindingResult接收校验出错信息
// 注意：@Validated和BindingResult bindingResult是配对出现，并且形参顺序是固定的（一前一后）。
// value={ValidGroup1.class}指定使用ValidGroup1分组的 校验
// @ModelAttribute可以指定pojo回显到页面在request中的key
@RequestMapping("/editItemsSubmit")
public String editItemsSubmit(
		Model model,
		HttpServletRequest request,
		Integer id,
		@ModelAttribute("items") @Validated(value = { ValidGroup1.class }) ItemsCustom itemsCustom,
		BindingResult bindingResult,
		MultipartFile items_pic//接收商品图片
		) throws Exception {
	// 获取校验错误信息
	if (bindingResult.hasErrors()) {
		// 输出错误信息
		List<ObjectError> allErrors = bindingResult.getAllErrors();
		for (ObjectError objectError : allErrors) {
			// 输出错误信息
			System.out.println(objectError.getDefaultMessage());
		}
		// 将错误信息传到页面
		model.addAttribute("allErrors", allErrors);
		
		
		//可以直接使用model将提交pojo回显到页面
		model.addAttribute("items", itemsCustom);
		
		// 出错重新到商品修改页面
		return "items/editItems";
	}
	//原始名称
	String originalFilename = items_pic.getOriginalFilename();
	//上传图片
	if(items_pic!=null && originalFilename!=null && originalFilename.length()>0){
		
		//存储图片的物理路径
		String pic_path = "F:\\develop\\upload\\temp\\";
		
		
		//新的图片名称
		String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
		//新图片
		File newFile = new File(pic_path+newFileName);
		
		//将内存中的数据写入磁盘
		items_pic.transferTo(newFile);
		
		//将新图片名称写到itemsCustom中
		itemsCustom.setPic(newFileName);
		
	}
	
	// 调用service更新商品信息，页面需要将商品信息传到此方法
	itemsService.updateItems(id, itemsCustom);
	// 重定向到商品查询列表
	// return "redirect:queryItems.action";
	// 页面转发
	// return "forward:queryItems.action";
	return "success";
}
jsp页面接收并显示错误信息：
<!-- 显示错误信息 -->
<c:if test="${allErrors!=null }">
	<c:forEach items="${allErrors }" var="error">
	${ error.defaultMessage}<br/>
	</c:forEach>
</c:if>
------------------------------------------------------------------
二：分组校验	
	在pojo中定义校验规则，而pojo是多个controller共用的，当需要不同的
controller方法对同一个pojo进行校验，而每个controller校验的规则不一样，
则此时需要用到分组校验。

步骤：
1.定义 VaildGroup1.java,VaildGroup2.java 分组接口
不需要在里面定义方法
2.在校验规则中（pojo）添加分组
@Size(min=1,max=30,message="{items.name.length.error}",groups={ValidGroup1.class})
private String name;
3.在controller中使用分组校验
public String editItemsSubmit(
		Model model,
		HttpServletRequest request,
		Integer id,
		@ModelAttribute("items") @Validated(value = { ValidGroup1.class }) ItemsCustom itemsCustom,
		BindingResult bindingResult,
		MultipartFile items_pic//接收商品图片
		) throws Exception {

}

@Validated(value = { ValidGroup1.class }) ItemsCustom itemsCustom,
指定该参数只校验该分组的校验规则
//使用分组一、分组二进行校验
public String insertUser(Model model,@Validated(value={ValidationGroup1.class,ValidationGroup2.class}) User user,BindingResult bindingResult,Integer uid){
	
}