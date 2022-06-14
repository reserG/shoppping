package com.example.seckillcontroller.interceptor;

import com.example.common.enums.ResultStatus;
import com.example.common.exception.GlobleException;
import com.example.common.utils.resultbean.ResultMAX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.example.common.enums.ResultStatus.SESSION_ERROR;


/**
 * @author 邱润泽
 * @ExceptionHandler 拦截了异常，我们可以通过该注解实现自定义异常处理。
 * 其中，@ExceptionHandler 配置的 value 指定需要拦截的异常类型，上面拦截了 Exception.class 这种异常。
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {


    public static Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    @ResponseBody
    @ExceptionHandler(value = GlobleException.class)
    public ResultMAX<String> globalException(Exception e) {
        e.printStackTrace();
        GlobleException ex = (GlobleException) e;
        return ResultMAX.error(ex.getStatus());
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public ResultMAX<String> bindException(Exception e) {
        e.printStackTrace();
        BindException ex = (BindException) e;
        List<ObjectError> errors = ex.getAllErrors();
        ObjectError error = errors.get(0);
        String msg = error.getDefaultMessage();

        logger.error(String.format(msg, msg));
        ResultMAX resultMAX = ResultMAX.error(ResultStatus.BIND_ERROR);
        resultMAX.withError(msg);
        return resultMAX;
    }



}
