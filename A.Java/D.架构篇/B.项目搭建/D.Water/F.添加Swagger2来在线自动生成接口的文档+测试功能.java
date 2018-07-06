1.什么是 Swagger
2.使用步骤







-------------------------------------------------------
1.什么是 Swagger
Swagger 是1款通过我们添加的注解来对方法进行说明，来自动生成项目的在线 api 接口文档的 web 服务。







-----------------------
2.使用步骤
1)添加 Swagger2 依赖
<dependency>
   <groupId>io.springfox</groupId>
   <artifactId>springfox-swagger2</artifactId>
   <version>2.4.0</version>
</dependency>
<dependency>
   <groupId>io.springfox</groupId>
   <artifactId>springfox-swagger-ui</artifactId>
   <version>2.4.0</version>
</dependency>

-----
2)创建 Swagger2 配置文件
在文件夹 configurer 中创建 SwaggerConfigurer
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigurer {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zhc.water.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("mySpringBoot 使用Swagger2构建RESTful APIs")
                .description("更多Spring Boot相关文章请关注：https://juejin.im/user/59e7fb9451882578e1406a51/posts")
                .termsOfServiceUrl("https://juejin.im/user/59e7fb9451882578e1406a51/posts")
                .contact(new Contact("idoit0", "https://juejin.im/user/5a732bc35188257a654ca6f6", null))
                .version("1.0")
                .build();
    }
}

-----
3)修改 Controller，添加API注解

import com.zhc.water.model.User;
import com.zhc.water.service.UserService;
import com.zhc.water.utils.HttpEntity;
import com.zhc.water.utils.HttpStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("user")
@Api(tags = {"用户操作接口"}, description = "UserControler")
public class UserController {
    @Resource
    private UserService userService;
    @ApiOperation(value = "查询用户", notes = "根据用户编码查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "User", value = "用户", required = true,
                    dataType = "User", paramType = "query")
    })
    @PostMapping("getUserByUserCode")
    public HttpEntity getUserByUserCode(@RequestBody User user){
        try{
            if(user != null && !StringUtils.isEmpty(user.getUserCode())){
                List<User> userList = userService.selectByUserCode(user.getUserCode());
                return new HttpEntity(HttpStatus.OK,true,"请求成功",userList);
            }
            return new HttpEntity(HttpStatus.OK,true,"找不到数据","");
        }catch (Exception e){
            return new HttpEntity(HttpStatus.OK,false,e.getMessage(),"");
        }
    }
}

-----
4)打开页面进行接口测试
浏览器输入 localhost:8080/swagger-ui.html
如果出现404，则修改 WebMvcConfigurationSupport 配置。
在 WebConfigurer 中添加如下代码

@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    registry.addResourceHandler("/favicon.ico")
            .addResourceLocations("classpath:/META-INF/resources/favicon.ico");
    super.addResourceHandlers(registry);
}