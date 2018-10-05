1.统1返回的数据格式
2.统1处理异常
3.编译与信息要写为枚举类，统1管理








-------------------------------------------------------------------------------------------------------------------------------------------------
1.统1返回的数据格式
1.1:返回结果封装
Result.java
/**
 * 返回结果封装
 * Created By Administrator
 * on 2018/10/1/001
 */
public class Result<T> {
    /**
     * 返回结果编码
     */
    private String code;
    /**
     * 返回结果信息
     */
    private String msg;
    /**
     * 返回结果数据
     */
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
--------------------
1.2:返回结果封装工具类
package com.imooc.utils;

import com.imooc.domain.CodeMsg;
import com.imooc.domain.Result;

/**
 * 返回结果封装工具类
 * Created By Administrator
 * on 2018/10/1/001
 */
public class ResultUtil {
    /**
     * 请求成功并返回数据
     * @param data
     * @return
     */
    public static Result success(Object data){
        Result result = new Result();
        result.setCode("success");
        result.setMsg("请求成功");
        result.setData(data);
        return result;
    }

    /**
     * 请求成功但是不返回数据
     * @return
     */
    public static Result success(){
        Result result = new Result();
        result.setCode("success");
        result.setMsg("请求成功");
        result.setData(null);
        return result;
    }

    public static Result fail(CodeMsg codeMsg){
        Result result = new Result();
        result.setCode(codeMsg.getCode());
        result.setMsg(codeMsg.getMsg());
        return result;
    }
}


------------------------
1.3:异常信息类封装
package com.imooc.domain;

/**
 * 异常信息封装
 * Created By Administrator
 * on 2018/10/1/001
 */
public class CodeMsg {
    /**
     * 异常编码
     */
    private String code;
    /**
     * 异常信息
     */
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CodeMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

--------------------------
1.4:Controller 层使用例子
package com.imooc.controller;
import com.imooc.domain.CodeMsg;
import com.imooc.domain.Girl;
import com.imooc.domain.Result;
import com.imooc.repository.GirlRepository;
import com.imooc.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * Created By Administrator
 * on 2018/9/29/029
 */
@RestController
public class GirlController {
    @Autowired
    private GirlRepository girlRepository;

    @GetMapping(value = "/list")
    public Result findAll(){
       return ResultUtil.success(girlRepository.findAll());
    }

    @PostMapping(value = "/findByAge")
    public Result findByAge(@RequestBody Girl girl){
        Integer age = girl.getAge();
        return ResultUtil.success((List<Girl>) girlRepository.findByAge(age));
    }

    @PostMapping(value = "/add")
    public Result add(@Valid  @RequestBody Girl girl, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.fail(new CodeMsg("500",bindingResult.getFieldError().getDefaultMessage()));
        }
        return null;
    }
}



-------------------------------------------------------------------------
2.统1处理异常
注意:处理之后，对应的 Service 与 Controller 层只需要将异常直接抛出即可。
	如果要抛出具体的异常信息，就抛出自定义异常类异常即可。
	如下:

	@PostMapping(value = "/add")
    public Result add(@Valid  @RequestBody Girl girl, BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors()){
            throw new GirlException("500",bindingResult.getFieldError().getDefaultMessage());
        }
        return null;
    }


2.1:自定义异常处理类
package com.imooc.exception;
public class GirlException extends RuntimeException{
    public GirlException(String code,String message){
        super(message);
        this.code = code;
    }
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}


---------------------------
2.2:统1异常处理类
package com.imooc.handle;
import com.imooc.domain.CodeMsg;
import com.imooc.domain.Result;
import com.imooc.exception.GirlException;
import com.imooc.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof GirlException){
            return ResultUtil.fail(new CodeMsg(((GirlException) e).getCode(),e.getMessage()));
        }else{
            return ResultUtil.fail(new CodeMsg("-1","未知异常"));
        }
    }
}



-------------------------------------------------------------------------
3.编译与信息要写为枚举类，统1管理
结果枚举类:
package com.imooc.enums;
public enum ResultEnum {
    UNKONW_EROOR("-1","未知错误"),
    SUCCECC("0","成功"),
    PRIMARY_SCHOOL("100","你在上小学吗"),
    MIDDELE_SCHOOL("101","你在上初中吗")
    ;

    private String code;
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}


自定义异常处理类
import com.imooc.enums.ResultEnum;

/**
 * 自定义异常处理类
 * Created By Administrator
 * on 2018/10/1/001
 */
public class GirlException extends RuntimeException{
    public GirlException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}


Controller:
	@PostMapping(value = "/add")
    public Result add(@Valid  @RequestBody Girl girl, BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors()){
            throw new GirlException(ResultEnum.MIDDELE_SCHOOL);
        }
        return null;
    }