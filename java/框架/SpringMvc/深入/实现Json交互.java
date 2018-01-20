Json 数据交互

@RequestBody :入参
将请求的 Json 格式数据转换为 Java 对象
@ResponseBody:出参
将 Java 对象转成 Json 串输出

Json 数据理解，详情请看
H:\自我提升\JavaWeb学习视频\后台\Springmvc+Mybatis由浅入深全套视频教程\【IT教程网】springmvc\springmvc\day02\res\第二天课堂笔记
8.json数据交互

通常的两种方法:
1.请求 Json ,输出 Json ,
  要求请求的是 Json 串，所以在前端页面中需要将请求的内容转成 Json ,不方便
2.请求 key/value，输出 Json 。
  此方法比较常用，请求简单。


目录:
1.为什么要进行 Json 数据交互
2.环境准备
3.代码实例

--------------------------------
1.为什么要进行 Json 数据交互
  Json 格式数据应用于接口调用中，html页面显示，格式简单，解析方便。
  
--------------------------------
2.环境准备
	2.1.引入jar包
	2.2.配置 Json 转换器
		2.2.1.在注解适配器中加入messageConverter或者使用<mvc:annotation-driven/>则不用定义上边的内容。	
@RequestBody 和 @ResponseBody  就是使用jar包进行 Json 转换的。

--------------------------------
3.代码实例

<script type="text/javascript">
//请求json，输出是json
function requestJson(){
	
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath }/requestJson.action',
		contentType:'application/json;charset=utf-8',
		//数据格式是json串，商品信息
		data:'{"name":"手机","price":999}',
		success:function(data){//返回json结果
			alert(data);
		}
		
	});
	
	
}
//请求key/value，输出是json
function responseJson(){
	
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath }/responseJson.action',
		//请求是key/value这里不需要指定contentType，因为默认就 是key/value类型
		//contentType:'application/json;charset=utf-8',
		//数据格式是json串，商品信息
		data:'name=手机&price=999',
		success:function(data){//返回json结果
			alert(data.name);
		}
		
	});
	
}
</script>
</head>
<body>
<input type="button" onclick="requestJson()" value="请求json，输出是json"/>
<input type="button" onclick="responseJson()" value="请求key/value，输出是json"/>

java:
@Controller
public class JsonTest {
	
	//请求json串(商品信息)，输出json(商品信息)
	//@RequestBody将请求的商品信息的json串转成itemsCustom对象
	//@ResponseBody将itemsCustom转成json输出
	@RequestMapping("/requestJson")
	public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom){
		
		//@ResponseBody将itemsCustom转成json输出
		return itemsCustom;
	}
	
	//请求key/value，输出json
	@RequestMapping("/responseJson")
	public @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom){
		
		//@ResponseBody将itemsCustom转成json输出
		return itemsCustom;
	}

}