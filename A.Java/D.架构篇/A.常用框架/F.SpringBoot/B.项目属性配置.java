1.application.yml
2.通过注解获取属性
3.不同环境下切换配置文件



---------------------------------------------------------------------------------------------------------------------------------------------------
1.application.yml
server:
  #端口
  port: 8888
  servlet:
    #项目名
    context-path: /imooc


-------------------------------------------------------------------
2.通过注解获取属性

2.1:获取单个属性
#自定义
cupSize: B
age: 18
content: "cupSize: ${cupSize},age:${age}"

server:
  #端口
  port: 8888
  servlet:
    #项目名
    context-path: /imooc


@Value("${cupSize}")
private String cupSize;
@Value("${age}")
private Integer age;
@Value("${content}")
private String content;


2.2:获取分组下的属性
#自定义
girl:
  cupSize: B
  age: 18
  content: "cupSize: ${cupSize},age:${age}"
server:
  #端口
  port: 8888
  servlet:
    #项目名
    context-path: /imooc

定义实体类
//注入容器
@Component
//获取前缀是girl的配置
@ConfigurationProperties(prefix = "girl")
public class GirlProperties {
    private String cupSize;
    private Integer age;

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}



public class HelloController {
    @Autowired
    GirlProperties girlProperties;

    @RequestMapping(value = "/hello2",method = RequestMethod.POST)
    public String hello2(){
        return girlProperties.getCupSize()+girlProperties.getAge();
    }
}


-------------------------------------------------------------------
3.不同环境下切换配置文件
----------
主配置文件指定哪个，就使用哪个
主配置文件:
application.yml

spring:
  profiles:
    #使用开发环境
    #active: dev
    #使用生产环境
    active: prod

----------
开发环境配置文件:
application-dev.yml

#自定义
girl:
  cupSize: B
  age: 18
  content: "cupSize: ${cupSize},age:${age}"
server:
  #端口
  port: 8888
  servlet:
    #项目名
    context-path: /imooc

----------
生产环境配置文件:
application-prod.yml

#自定义
girl:
  cupSize: F
  age: 28
  content: "cupSize: ${cupSize},age:${age}"
server:
  #端口
  port: 8888
  servlet:
    #项目名
    context-path: /imooc

