package com.qf.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MyInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        String name = null;

        System.out.println("cookies = " + cookies);
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")){
                name = cookie.getValue();
                System.out.println("============="+name);
            }
        }
        // TODO 利用 redis 进行存储
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(name);
        if (attribute != null){
            // 已登录
            List attribute1 = (List) session.getAttribute(name + "_per");
//            System.err.println("attribute1 = " + attribute1);
            if (attribute1.contains(request.getRequestURI())){
                return true;
            }else {
//                response.setCharacterEncoding("UTF-8");
//                response.setContentType("application/json");
//                ResultMessage resultMessage = new ResultMessage();
//                resultMessage.setCode(301);
//                resultMessage.setMessage("没有权限，不允许访问！");
//                Object o = JSONObject.toJSON(resultMessage);
//                PrintWriter writer = response.getWriter();
//                writer.print(o);
                return false;
            }
        }
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json");
//        ResultMessage resultMessage = new ResultMessage();
//        resultMessage.setCode(302);
//        resultMessage.setMessage("未登录，不允许访问！");
//        Object o = JSONObject.toJSON(resultMessage);
//        PrintWriter writer = response.getWriter();
//        writer.print(o);
        return false;
    }
}
