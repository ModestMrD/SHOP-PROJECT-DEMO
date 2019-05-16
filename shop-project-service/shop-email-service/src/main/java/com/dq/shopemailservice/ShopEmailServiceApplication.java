package com.dq.shopemailservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.dq.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopEmailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopEmailServiceApplication.class, args);
	}

}
