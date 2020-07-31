package cn.com.rivercloud.wechat.common.exception;

import cn.com.rivercloud.wechat.common.lang.JsonResponseBuilder;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 表单参数验证
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public JSONObject handler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return new JsonResponseBuilder().success(false).message(objectError.getDefaultMessage()).build();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public JSONObject handler(IllegalArgumentException e) {
        System.out.println(e);
        return new JsonResponseBuilder().success(false).message(e.getMessage()).build();
    }


    // json 数据格式错误
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public JSONObject handler(HttpMessageNotReadableException e) {
        System.out.println(e);
        return new JsonResponseBuilder().success(false).message("数据格式错误").build();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public JSONObject handler(RuntimeException e) {
        System.out.println(e);
        return new JsonResponseBuilder().success(false).message(e.getMessage()).build();
    }

    @ExceptionHandler(value = Exception.class)
    public JSONObject handler(Exception e) {
        System.out.println(e);
        return null;
    }


}
