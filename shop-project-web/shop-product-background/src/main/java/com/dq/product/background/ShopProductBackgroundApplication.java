package com.dq.product.background;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.util.HashSet;

@SpringBootApplication
@EnableDubbo
@Import(FdfsClientConfig.class)
public class ShopProductBackgroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopProductBackgroundApplication.class, args);
	}
}
