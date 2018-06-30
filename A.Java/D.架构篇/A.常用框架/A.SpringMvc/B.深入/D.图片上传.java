图片上传功能：
    将图片上传到硬盘中，将图片名写入数据库，
页面显示的时候，img 标签直接指定绝对路径。

1.jsp页面中要指定上传类型，与上传控件

2.在springmvc.xml中配置文件上传

3.创建图片虚报目录，存储图片

4.加入上传图片的jar包

5.代码例子

------------------------------------------------------
1.jsp页面中指定form表单类型
<form id="itemForm" action="${pageContext.request.contextPath }/items/editItemsSubmit.action" 
method="post" enctype="multipart/form-data">
	<tr>
		<td>商品图片</td>
		<td>
			<c:if test="${items.pic !=null}">
				<img src="/pic/${items.pic}" width=100 height=100/>
				<br/>
			</c:if>
			<input type="file"  name="items_pic"/> 
		</td>
	</tr>
</form>

 
----------------------------------------------------
2.在springmvc.xml中配置文件上传
如果在页面表单指定提交的类型为
<form id="itemForm" action="${pageContext.request.contextPath }/items/editItemsSubmit.action" 
method="post" enctype="multipart/form-data"></form>

则必须在springmvc.xml配置文件上传bean,否则在controller中会获取不到数据

<!-- 文件上传 -->
<bean id="multipartResolver"
	class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
	<!-- 设置上传文件的最大尺寸为5MB -->
	<property name="maxUploadSize">
		<value>5242880</value>
	</property>
</bean>


----------------------------------------------
3.创建图片虚报目录，存储图片
3.1.双击tomcat服务器可以配置
3.2.也可以直接修改tomcat配置文件，server.xml
3.3.在图片虚拟目录中，一定将图片目录分级创建（提高IO性能）
	一般采用按日期进行分级创建。


----------------------------------------------
5.代码例子
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
		//model.addAttribute("items", itemsCustom);
		
		// 出错重新到商品修改页面
		return "items/editItems";
	}
	//拿到图片的原始名称
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
--
jsp:页面展示

img标签在src上加"/pic/${items.pic}"就能访问到硬盘中的图片，
是因为在tomcat中配置了虚拟上当，将pic设置为图片存储的路径名

<tr>
	<td>商品图片</td>
	<td>
		<c:if test="${items.pic !=null}">
			<img src="/pic/${items.pic}" width=100 height=100/>
			<br/>
		</c:if>
		<input type="file"  name="items_pic"/> 
	</td>
</tr>