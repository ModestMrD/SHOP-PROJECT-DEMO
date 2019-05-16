package com.dq.shopemailservice;

import com.dq.service.IEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopEmailServiceApplicationTests {

	@Autowired
	private IEmailService emailService;

	@Test
	public void contextLoads() {
		emailService.sendSimpleMail("349974952@qq.com","玩玩试试","潇潇洒洒");
	}

}
