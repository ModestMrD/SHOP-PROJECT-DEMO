package com.dq.msgcode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgcodeApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void contextLoads() {

	}

	@Test
	public void testSend(){
		redisTemplate.opsForValue().set("register:code:15927398273","6688");
		redisTemplate.expire("register:code:15927398273",10, TimeUnit.MINUTES);
		System.out.println("短信验证码发送成功！");
	}

	@Test
	public void check(){
		String currentCode = "1234";
		String code = (String) redisTemplate.opsForValue().get("register:code:15927398273");
		if (currentCode.equals(code)){
			System.out.println("验证码正确!");
			return;
		}
		System.out.println("验证码错误");
		currentCode = "6688";
		if (currentCode.equals(code)){
			System.out.println("验证码正确!");
			redisTemplate.delete("register:code:15927398273");
			return;
		}
		System.out.println("验证码错误");
	}

}
