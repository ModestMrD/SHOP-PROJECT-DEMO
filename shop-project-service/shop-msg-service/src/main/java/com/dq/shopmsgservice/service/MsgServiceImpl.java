package com.dq.shopmsgservice.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.dq.service.IMsgService;
import org.springframework.stereotype.Component;

/**
 * @author DuQian
 * @Date 2019/3/19
 */
@Component
public class MsgServiceImpl implements IMsgService {

    @Override
    public void sendMsg(String templateParam ,String phoneNumbers) {
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIKNQZ7OAs0qdh", "c9AWaRPZcwVbwXUqvkokonIIfCHJBg");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", "千峰商城");
        request.putQueryParameter("TemplateCode", "SMS_160861513");
        request.putQueryParameter("TemplateParam",templateParam);
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
