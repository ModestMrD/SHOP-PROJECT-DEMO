package com.dq.shop;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.dq.project.mapper")
public class ShopProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopProductServiceApplication.class, args);
	}

}
