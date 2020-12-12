package com.qf.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.IOException;
import java.util.UUID;

@Component
public class Alipay {

    @Value("${Alipay.serverUrl}")
    private String serverUrl;
    @Value("${Alipay.APP_ID}")
    private String APP_ID;
    @Value("${Alipay.APP_PRIVATE_KEY}")
    private String APP_PRIVATE_KEY;
    @Value("${Alipay.FORMAT}")
    private String FORMAT;
    @Value("${Alipay.CHARSET}")
    private String CHARSET;
    @Value("${Alipay.ALIPAY_PUBLIC_KEY}")
    private String ALIPAY_PUBLIC_KEY;
    @Value("${Alipay.SIGN_TYPE}")
    private String SIGN_TYPE;


    public   String   pay (HttpServletRequest httpRequest, HttpServletResponse httpResponse, Book book)   throws ServletException, IOException {

        AlipayClient alipayClient =  new DefaultAlipayClient( serverUrl , APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);  //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest(); //创建API对应的request
        alipayRequest.setReturnUrl( "http://domain.com/CallBack/return_url.jsp" );
        alipayRequest.setNotifyUrl( "http://4dslcsft.shenzhuo.vip:11239/returnUrl" ); //在公共参数中设置回跳和通知地址
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        String replace = s.replace("-", "");

        alipayRequest.setBizContent( "{"  +
                "    \"out_trade_no\":\""+replace+"\","  +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
                "    \"total_amount\":\""+ book +"\","  +
                "    \"subject\":\""+book+"\","  +
                "    \"body\":\""+book+"\","  +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","  +
                "    \"extend_params\":{"  +
                "    \"sys_service_provider_id\":\"2088511833207846\""  +
                "    }" +
                "  }" ); //填充业务参数
        String form= "" ;
        try  {
            form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        }  catch  (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType( "text/html;charset="  + CHARSET);
        /*httpResponse.getWriter().write(form); //直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();*/
        return form;

    }

}
