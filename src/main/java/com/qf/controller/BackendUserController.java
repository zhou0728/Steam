package com.qf.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class BackendUserController {


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(){

        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(tUser.getName(), tUser.getPassword());
        UsernamePasswordToken token = null;
        try {
            subject.login(token);
        }catch (Exception e){
            System.out.println("登陆失败1!");
        }
        if (subject.isAuthenticated()){
            return "findAll.html";
        }else {
            return "/login.html";
        }


    }

    @RequestMapping("/unauth")
    public String unauth(){
        return "unauth";
    }

}
