1.引入依赖:
2.自定义注解校验:
3.统1异常处理:








---------------------------------------------------
1.引入依赖:
	  <!--jsr303参数校验-->
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-validation</artifactId>
      </dependency>
      <!--jsr303参数校验-->



-----------------------------
2.自定义注解校验:
//实体校验
public class User {
    private int id;
    @NotNull
    private String account;
    @NotNull
    private String password;
    @NotNull
    @IsMobile
    private String phone;
}
//注解
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidator.class})
public @interface IsMobile {
    boolean required() default true;

    String message() default "手机格式有误！";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

//校验类
import com.imooc.miaosha.utils.ValidatorUtil;
import org.springframework.util.StringUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required = false;

    /**
     * 初始化方法可以获取到注解的信息
     * 获取是否必填字段
     * @param constraintAnnotation
     */
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //如果为必填
        if(required){
            return ValidatorUtil.isMobile(value);
        }else{
            if(StringUtils.isEmpty(value)){
                return true;
            }else{
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}

//校验规则工具类 ValidatorUtil.java
public class ValidatorUtil {
    /**
     * 校验手机号
     *
     * @param phone
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String phone) {
        if(phone.length()!=11){
            return false;
        }
        return true;
    }
}


//使用方式
import com.imooc.common.CodeMsg;
import com.imooc.common.Result;
import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.service.UserService;
import com.imooc.miaosha.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;
    @RequestMapping("checkMsg")
    public Result checkMsg(@Valid @RequestBody User userParam){

        //相关参数校验省略
        User user = userService.getUserByAccount(userParam.getAccount());
        if(user == null){
            return Result.error(CodeMsg.ACCOUNT_EMPTY);
        }
        //数据库存在的密码与盐
        String dbPassword = user.getPassword();
        String salt = user.getSalt();
        //前端一次md5加密加固定盐之后的密码
        String passwordParam = userParam.getPassword();
        //前端传递过来的密码，加上数据库的随机盐，再与已经保存的密码比较。如果是一致的，则证明密码正确。
        String password = MD5Util.formPassToDBPass(passwordParam,salt);
        if(!password.equals(dbPassword)){
            return Result.error(CodeMsg.PASSWORD_EROOR);
        }
        return Result.success(CodeMsg.LOGIN_SUCCESS);
    }
}

//返回异常示例
{
    "timestamp": 1533564805815,
    "status": 400,
    "error": "Bad Request",
    "exception": "org.springframework.web.bind.MethodArgumentNotValidException",
    "errors": [
        {
            "codes": [
                "IsMobile.user.phone",
                "IsMobile.phone",
                "IsMobile.java.lang.String",
                "IsMobile"
            ],
            "arguments": [
                {
                    "codes": [
                        "user.phone",
                        "phone"
                    ],
                    "arguments": null,
                    "defaultMessage": "phone",
                    "code": "phone"
                },
                true
            ],
            "defaultMessage": "手机格式有误！",
            "objectName": "user",
            "field": "phone",
            "rejectedValue": "1881946403",
            "bindingFailure": false,
            "code": "IsMobile"
        }
    ],
    "message": "Validation failed for object='user'. Error count: 1",
    "path": "/login/checkMsg"
}




-----------------------------
3.统1异常处理:
通过定义异常拦截器，拦截所有异常。将不同种类的异常对应的不同的处理方式的代码编写好。
结合JSR303校验框架，就可以将具体的异常信息抛出并返回给页面。


全局异常处理器
GlobleExceptionHandler.java

import com.imooc.miaosha.common.CodeMsg;
import com.imooc.miaosha.common.Result;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobleExceptionHandler {

    //拦截所有异常
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request,Exception e){
        //如果异常为数据绑定异常
        if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException)e;
            List<ObjectError> errors =  ex.getBindingResult().getAllErrors();
            String errorMsg = errors.get(0).getDefaultMessage();
           return Result.error(CodeMsg.BIND_ERROR.fillArgs(errorMsg));
        }
        //如果是其他异常，则抛出服务器异常
        else{
            return Result.error(CodeMsg.SERVICE_EROOR);
        }
    }
}

//异常信息类
CodeMsg.java
import java.util.Date;

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
    /**
     * 登录模块
     */
    //通用错误码
    public static CodeMsg BIND_ERROR = new CodeMsg(100001,"绑定异常:%s");
    //失败
    public static CodeMsg ACCOUNT_EMPTY = new CodeMsg(500001,"账号不存在");
    public static CodeMsg PASSWORD_EROOR = new CodeMsg(500002,"密码错误");
    public static CodeMsg SERVICE_EROOR = new CodeMsg(500500,"服务器异常");
    //成功
    public static CodeMsg LOGIN_SUCCESS = new CodeMsg(500100,"登录成功");

	//自定义异常信息
    public CodeMsg fillArgs(Object... args){
        int code = this.code;
        String message = String.format(this.msg,args);
        return new CodeMsg(code,message);
    }
}


注意:
@ControllerAdvice等注解
需要跟 MainApplication.java 在同一级下才会生效。