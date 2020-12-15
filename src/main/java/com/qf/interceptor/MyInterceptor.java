package com.qf.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.qf.common.ResultResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        String name = null;

        System.out.println("cookies = " + cookies);
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")){
                name = cookie.getValue();
            }
        }

        // TODO 利用 redis 进行存储
        Object o = redisTemplate.opsForValue().get(name);

        if (o != null){
            // 已登录,从redis中取出用户的可以访问的权限
//            List attribute1 = (List) session.getAttribute(name + "_per");
//            System.err.println("attribute1 = " + attribute1);
            System.out.println(request.getRequestURI());
            if ("/game/addShop".contains(request.getRequestURI())){
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                ResultResp resultResp = new ResultResp();
                resultResp.setCode(200);
                resultResp.setMessage("已登录，有权限访问");
                Object o1 = JSONObject.toJSON(resultResp);
                PrintWriter writer = response.getWriter();
                writer.print(o1);
                return true;
            }else {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                ResultResp resultResp = new ResultResp();
                resultResp.setCode(301);
                resultResp.setMessage("没有权限，不允许访问！");
                Object o3 = JSONObject.toJSON(resultResp);
                PrintWriter writer = response.getWriter();
                writer.print(o3);
                return false;
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResultResp resultResp = new ResultResp();
        resultResp.setCode(302);
        resultResp.setMessage("请登录！");
        Object o2 = JSONObject.toJSON(resultResp);
        PrintWriter writer = response.getWriter();
        writer.print(o2);
        return false;
    }
}
