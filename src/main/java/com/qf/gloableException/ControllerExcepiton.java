package com.qf.gloableException;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by 54110 on 2020/12/7.
 */

//控制器增强  只能捕捉发生在controller的异常
@ControllerAdvice
public class ControllerExcepiton {

    //代表当前类捕捉的异常
    @ExceptionHandler(AuthorizationException.class)
    public String exception(){

 /*       BaseResult baseResult = new BaseResult();
        baseResult.setCode(201);
        baseResult.setMessage("无限去年");*/
        return "unauth.html";
}
}
