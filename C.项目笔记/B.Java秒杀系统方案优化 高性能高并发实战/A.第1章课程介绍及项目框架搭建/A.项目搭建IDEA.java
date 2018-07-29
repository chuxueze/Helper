1.新建项目:
2.集成 thymeleaf
3.构建 RestAPI 风格统1返回结果集对象








---------------------------------------------
1.新建项目:
创建项目:
new Project->maven->maven-archetye-quickstart

1.1:导入依赖:
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>1.5.8.RELEASE</version>
</parent>

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>

--------------
1.2:新建启动类:
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}

----------------------------------
2.集成 thymeleaf

2.1:导入依赖
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

--------------
2.2:新建 resources 文件夹

SpringBoot 会默认加载 application.properties 配置文件
application.properties

server.port=8080
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.check-template-location=true
spring.thymeleaf.suffix=.html
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.content-type=text/html
spring.thymeleaf.mode=HTML5

示例代码:
resources templates 下创建 hello.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
	<title>Hello</title>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
</head>
<body>
  <p th:text="'hello:'+${name}"></p>
</body>
</html>

对应 controller
@Controller
@RequestMapping("/demo")
public class SampleController {
    @RequestMapping("thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","zhc");
        System.out.println("访问到了。。。。。。。");
        return "hello";
    }
}


----------------------------------
3.构建 RestAPI 风格统1返回结果集对象
3.1:
Result: 结果集类，用于统1返回数据结果
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    /**
     * 成功时调用
     * data:成功时返回的数据
     * @return
     */
    public static <T>Result<T> success(T data){
        return new Result<T>(data);
    }

    /**
     * 初始化调用成功的构造函数
     * @param data
     */
    private Result(T data){
        this.code = 1;
        this.msg = "success";
        this.data = data;
    }

    /**
     * 失败时调用
     * codeMsg
     * code:错误编码
     * msg:错误信息
     * @param codeMsg
     * @return
     */
    public static Result error(CodeMsg codeMsg){
        return new Result(codeMsg);
    }

    /**
     * 初始化调用失败的构造函数
     * @return
     */
    private Result(CodeMsg codeMsg){
        if(codeMsg == null)  return;
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
----------
3.2:
CodeMsg 信息封装类，程序出现错误时，封装错误信息。
public class CodeMsg {
    private int code;
    private String msg;

    /**
     * code:错误编码
     * msg:错误信息
     * @param code
     * @param msg
     */
    public CodeMsg(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
----------
3.3:
Controller 例子
//restAPI Json输出接口
    @RequestMapping("testSuccessJson")
    @ResponseBody()
    public Result testSuccessJson(){
        Set<String> set = new HashSet<String>();
        set.add("张浩纯");
        set.add("25岁");
        set.add("未婚");
        return Result.success(set);
    }
    @RequestMapping("testErrorJson")
    @ResponseBody()
    public Result testErrorJson(){
        return Result.error(new CodeMsg(500,"服务器异常"));
    }
