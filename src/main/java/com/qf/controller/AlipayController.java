package com.qf.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.qf.utils.Alipay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@RestController
public class AlipayController {

    @Autowired
    Alipay alipay;

    @Autowired
//    BookService bookService;

    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    public void pay(HttpServletRequest request, HttpServletResponse response, @RequestBody Map map) throws ServletException, IOException {
//        ResultResp resultResp = bookService.findById((Integer) map.get("id"));
//        Book book = (Book) resultResp.getData();
//        String form = alipay.pay(request, response,book);
//        response.getWriter().write(form); //直接将完整的表单html输出到页面
//        response.getWriter().flush();
//        response.getWriter().close();

    }

    @Value("${Alipay.ALIPAY_PUBLIC_KEY}")
    private String ALIPAY_PUBLIC_KEY;
    @Value("${Alipay.CHARSET}")
    private String CHARSET;
    @Value("${Alipay.SIGN_TYPE}")
    private String SIGN_TYPE;


    @RequestMapping("/returnUrl")
    public void returnUrl(HttpServletRequest request) throws AlipayApiException {

        Map<String, String> stringStringMap = convertRequestParamsToMap(request);
        boolean signVerified = AlipaySignature.rsaCheckV1(stringStringMap, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); //调用SDK验证签名

        if(signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
//            System.out.println("stringStringMap = " + stringStringMap);
            if (stringStringMap.get("trade_status").equals("TRADE_SUCCESS")){
                //out_trade_no订单已支付，修改数据库
                System.err.println(" 已支付");
            }
        }else{
            // TODO 验签失败则记录异常日志，并在response中返回failure。
        }

    }


    // 将request中的参数转换成Map
    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<String, String>();

        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }

        return retMap;
    }
}
