数据回显概念:
	提交后，如果出现错误，将刚才提交的数据回显到刚才的提交页面。

数据回显方法:
1.springmvc默认对pojo数据进行回显。
pojo数据传入controller方法后，springmvc自动将pojo数据放到request域。
@ModelAttribute 可以指定pojo回显到页面在request中的key

1.@ModelAttribute 指定pojo回显到页面在request中的key
2.@ModelAttribute 还可以将方法的返回值传到页面

@ModelAttribute  和  model.addAttribute("items", itemsCustom);
这两个方法效果相同





1.@ModelAttribute 指定pojo回显到页面在request中的key
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
---------------------------------------------------------------------

2.@ModelAttribute 还可以将方法的返回值传到页面
// 商品分类
//itemtypes表示最终将方法返回值放在request中的key
@ModelAttribute("itemtypes")
public Map<String, String> getItemTypes() {
	Map<String, String> itemTypes = new HashMap<String, String>();
	itemTypes.put("101", "数码");
	itemTypes.put("102", "母婴");
	return itemTypes;
}
jsp:
商品类型：
<select name="itemtype">
	<c:forEach items="${itemtypes }" var="itemtype">
		<option value="${itemtype.key }">${itemtype.value }</option>		
	</c:forEach>
</select>