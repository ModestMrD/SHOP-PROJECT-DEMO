package com.dq.shop;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.dq.project.mapper")
@EnableDubbo
public class ShopItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopItemServiceApplication.class, args);
	}

}
