package com.dq.shoppaybackground.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DuQian
 * @Date 2019/3/27
 */
@Controller
@RequestMapping("pay")
public class PayController {

    //支付宝官方说明，这个同步回调，不能说明支付成功
    @RequestMapping("success")
    public void success(){
        System.out.println("支付成功");;
    }

    @RequestMapping("notify")
    public String notifyResult(HttpServletRequest request){
        //1,验签，确认这个消息是支付宝给我的
        String appId = request.getParameter("app_id");//开发中appid
        //2,获取支付状态
        String tradeStatus = request.getParameter("trade_status");//支付状态
        //3,对照业务数据（10001 8888 支付宝流水号 对账）
        String outTradeNo = request.getParameter("out_trade_no");//订单编号
        String totalAmount = request.getParameter("total_amount");//订单金额
        String receiptAmount = request.getParameter("receipt_amount");//实收金额
        if (!"2016092600603970".equals(appId)||!"TRADE_SUCCESS".equals(tradeStatus)
                ||!"101".equals(outTradeNo)||!totalAmount.equals(receiptAmount)){
            return "fail";
        }
        //4,更新订单的状态为已支付
        //orderService.success(outTradeNo);
        return "success";
    }
    @RequestMapping("payAli")
    public void payAli(HttpServletRequest httpRequest,
                    HttpServletResponse httpResponse, String orderNo) throws ServletException, IOException {
        //商户调用支付宝的接口，之后就是支付宝跟用户直接对接
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do",
                "2016092600603970",
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCUB7qj/sux1ZIuPoA3y9kEKPTJnzyX50HslxQ6WK/8mtiB0ruTS+gU4y4tV9jEAdKZpgJQ/x/MRaNe+JNeC451D9xdqDG5JdsGsz1ppo/oYIMqPUrhrf70AxOB9RDyS3eqOnqsiV6tkcdFOsAgStEgTjGJ3PYL15PT/GSEIvMybqFIWZ4Fa1qINCE31XlcskBlMqIyQkwTftP8bdAHMBIZJ0aA7VTaMHjk9zeWX9I6d/vrBziwgO/t8D3qYfi9+6OOSwlF7L1f+cohudQDJZwYGQGvbPVWWJwHi+kvq+1SClG6EtqxBgbi0tz3KJ7gwTqKgY3VifItjCxpCJGLcA8jAgMBAAECggEAVPI6gHFuopZUVIU5PsuTtXdrQWhHdUQX6JSp3vGVy/dv1s//I/+jJplXnl2S3qfNGq7RFXnR3X74yT8MEiO/lB2N75DdN8opbibdTwtt+YOowUx69BbM/2nkf+cJ2oDEIsN8MhaCZkgJegjEx8MnTsqQUvShnRoQks17QPhtZrp/p6avKCoWnNaPCpabR2ISx4JyGNhigt0X9igTv0uNuBFqohD4OSFPJtRjL4YjQQGC0smbtUtT8MxniF0zc3YTU/bwemlqoN+seLtl1WmREoSUxTzpifjJxwP/cD5yFDJvR9uVpxcrHMRGo+2d9gJhkbJxiTsvs1INpf0xI3BpAQKBgQD3VNf2PbQ3acXuOf8m/ZAVcecZKUTrYzokRlXZkfJyC7yV9s5T1aZK6HSuXqegH7U7lfbmtjbkCwMrmQBdFCQBnhIQ7JOYC9kAQ5EfJaSvstFEGDroNKJz9O36aiCflKyTjhXY4ehLhkOVzKxwDig0ZITRn4iMbZxwvhgXV6D3MwKBgQCZN+pGQaKPmoBqa6Mzngx6t+bjhPhmOJNbVgg2OxYATo14C2BWEIJ/1WLUH4glbAgoQq6Gztbqv7C7Q5JSFpTgjKkeyT5RpAAGGAwSeG+5uSjCerQMGsecjndVZK4xyRkan5R3sGyf1ZDxZUTljdEd+PqW4hw8+NThIi4E5hbIUQKBgQDLNO0exkGMCZ5yhToKeefohqXduVeDogoCBbvBPzUX2GbAO3/3xyCSiPxcyy4j1AleBG9PzMO6hwW6y0FOOB8BKTMkrorxMELBAcJ4+b+eD2f574Juh9g1NzZTOODG3CsSLHnssGbfrldW+0tt4WPM8A1kARP6dJBE3RoEC54XmwKBgQCVtsNenyffNlMgqciw2VDMYQJlczrdd47Vgr1HcHjRF/C4SjV6I2vAyZtmNuFr36Ig5Pdwlo/+lgx1D1PcjmAvAoMGqBIS6DN1wO2a5sAVNE4Vqpxf75L48WS9tkFYOG4lS6VhRqz9iUu19QRbYjXiGbWS1aJOGb65thJNix7b4QKBgAe4a2xFq3WE2DOVWD+gBz0/XI7C8b0lnjdki7fB1VUdGJikEvwYaBs/DemCgeU5AKV6k/uLBnEJIm2zJwXVunw4IbtPKoTb7BWv5MHednG801L9GBRV+HBhYjbwuUQeyHbN6vYKan+OAPhnmK/Qz5Xy8QdDJR0gLPi/LapUGWQW",
                "json",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwkz5NNzXt9IcV8sheHaYskJ7G1kOEAyR5cn5CUw0iVqHURqokImbvNAILwMqRy4forXJNKM4CcjbuhigTmMxx6CYnn6n1yE8BHW1VGeFoJP9zFMtwTZboHHLteHCmWD0QDjj9yqhGxuM9Il6vPX/gtcpe5fLDY6yvFr9vSlD3q60GCkOvOScpL1YKmdFU28A7tz6O4V/IUhZdwM4LOdZCCNpTKou75lFT1hUuTarMbl9nD40ntGv6FeY1QeDNEXMyTJa8yfUwjUOs/ixkvyfcVAa5GNLygKjg7IoZ3CSA06GIuZUF16cdatOfFGWclr/6Fx/x8ubaZzg9t6zgOCU3wIDAQAB",
                "RSA2");
        //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://huangguizhao.natapp1.cc/order/success");
        alipayRequest.setNotifyUrl("http://huangguizhao.natapp1.cc/order/notify");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+orderNo+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":8888888," +
                "    \"subject\":\"香港公寓一间\"," +
                "    \"body\":\"香港公寓一间\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"" +
                "  }");//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=utf-8");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }
}
