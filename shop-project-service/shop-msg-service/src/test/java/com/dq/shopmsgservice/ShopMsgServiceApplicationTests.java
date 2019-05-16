package com.dq.shopmsgservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dq.service.IMsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopMsgServiceApplicationTests {

	@Autowired
	private IMsgService msgService;

	@Test
	public void contextLoads() {
		msgService.sendMsg("{\"code\":\"1234\"}","15927398273");
	}
}
