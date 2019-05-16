package com.dq.shopprojectindex;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.project.entity.TProduct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class ShopProjectIndexApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopProjectIndexApplication.class, args);
	}

}
