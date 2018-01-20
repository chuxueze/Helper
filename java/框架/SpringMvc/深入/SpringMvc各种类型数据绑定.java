目录:
    1.参数绑定默认支持的类型
    2.简单类型绑定
    3.pojo类型绑定
    4.数组绑定
    5.list绑定
    6.map绑定

--------------------------------------
--------------------------------------    
--------------------------------------
1.参数绑定默认支持的类型

1.1:默认支持的类型
直接在controller方法形参上定义下边类型的对象，就可以使用这些对象。在参数绑定过程中，如果遇到下边类型直接进行绑定。
1.1.1	HttpServletRequest
通过request对象获取请求信息
1.1.2	HttpServletResponse
通过response处理响应信息
1.1.3	HttpSession
通过session对象得到session中存放的对象
1.1.4	Model/ModelMap
model是一个接口，modelMap是一个接口实现 。
作用：将model数据填充到request域。
--------------------------------------
2.简单类型绑定

通过 @RequestParam 对简单类型的参数进行绑定。
如果不使用 @RequestParam，要求request传入参数名称和controller方法的形参名称一致，方可绑定成功。
public String editItems(Model model,@RequestParam(value="id",required=true,defaultValue="1") Integer items_id)throws Exception {
	
	//调用service根据商品id查询商品信息
	ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
	
	//通过形参中的model将model数据传到页面
	//相当于modelAndView.addObject方法
	model.addAttribute("itemsCustom", itemsCustom);
	
	return "items/editItems";
}
将items_id映射为id，当传入的入参为id，后台也可以用自定义的参数变量名去接收。
required:是否为必传的参数，defaultValue:如果没有传入参数，则给一个默认值

-------------------------------------
3.pojo类型绑定
成功前提：
3.1:页面中的input的name和controller中的pojo形参中的属性名一致

//商品信息修改提交
@RequestMapping("/editItemsSubmit")
public String editItemsSubmit(HttpServletRequest request,Integer id,ItemsCustom itemsCustom)throws Exception {
	
	//调用service更新商品信息，页面需要将商品信息传到此方法
	itemsService.updateItems(id, itemsCustom);
	
	//重定向到商品查询列表
	return "redirect:queryItems.action";
	//页面转发
	//return "forward:queryItems.action";
	//return "success";
}


3.2:Vo 包装类型的pojo，pojo里面包含pojo

jsp:商品名称：<input name="itemsCustom.name" />
注意：itemsCustom和包装pojo中的属性一致即可

controller方法形参：
public ModelAndView queryItems(HttpServletRequest request,ItemsQueryVo itemsQueryVo) throws Exception

ItemsQueryVo 里面包含着 itemsCustom


------------------------------------- 
4.数组绑定:

举例，批量删除，定义一个checkbox,将id传入
<c:forEach items="${itemsList }" var="item">
<tr>	
	<td><input type="checkbox" name="items_id" value="${item.id}"/></td>
	<td>${item.name }</td>
	<td>${item.price }</td>
	<td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<td>${item.detail }</td>
	
	<td><a href="${pageContext.request.contextPath }/items/editItems.action?id=${item.id}">修改</a></td>

</tr>
</c:forEach>

public String deleteItems(Integer[] items_id) throws Exception {
	// 调用service批量删除商品
	// ...
	return "success";
}
------------------------------------- 
5.list绑定
使用 List 接收页面提交的批量数据时，通过包装的pojo接收，在包装的pojo中定义一个list 属性，
action的入参就是些包装的类型  Vo;

public class ItemsQueryVo {
	
	//商品信息
	private Items items;
	
	//为了系统 可扩展性，对原始生成的po进行扩展
	private ItemsCustom itemsCustom;
	
	//批量商品信息
	private List<ItemsCustom> itemsList;
}
jsp:
查询之后，将数据显示在页面中 itemsList，修改之后点击批量修改，将 List 数据传递到后台。
varStatus:可以获取list的下标。
<c:forEach items="${itemsList }" var="item" varStatus="status">
<tr>	

	<td><input name="itemsList[${status.index }].name" value="${item.name }"/></td>
	<td><input name="itemsList[${status.index }].price" value="${item.price }"/></td>
	<td><input name="itemsList[${status.index }].createtime" value="<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/></td>
	<td><input name="itemsList[${status.index }].detail" value="${item.detail }"/></td>

</tr>
</c:forEach>
action:
// 批量修改商品提交
// 通过ItemsQueryVo接收批量提交的商品信息，将商品信息存储到itemsQueryVo中itemsList属性中。
@RequestMapping("/editItemsAllSubmit")
public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo)
		throws Exception {
	......
	return "success";
}
------------------------------------- 
6.map绑定
在pojo中包装map类型
jsp:
<tr>
	<td>学生信息</td>
	<td>
		<input type="text" name="itemInfo['name']"/>
		<input type="text" name="itemInfo['price']"/>
	</td>
</tr>
action:
public String useraddsubmit(Model model,QueryVo queryVo)throws Exception{
	System.out.println(queryVo.getStudentinfo());
}
