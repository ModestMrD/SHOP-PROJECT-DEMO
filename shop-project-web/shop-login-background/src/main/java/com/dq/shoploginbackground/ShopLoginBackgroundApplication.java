package com.dq.shoploginbackground;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class ShopLoginBackgroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopLoginBackgroundApplication.class, args);
	}

}
