package com.dq.shopcartservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.dq.common.POJO.ResultBean;
import com.dq.service.ICartService;
import com.dq.vo.CartItemVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopCartServiceApplicationTests {

	@Autowired
	private ICartService cartService;

	@Test
	public void addTest() {
		cartService.addCart("user:cart:6699", 2L, 10);
	}

	@Test
	public void listTest() {
		ResultBean<CartItemVO> resultBean = cartService.cartList("user:cart:6699");
		List<CartItemVO> list = (List<CartItemVO>) resultBean.getData();
		for (CartItemVO cartItemVO : list) {
			System.out.println(cartItemVO.getProduct());
		}
	}

	@Test
	public void deleteTest() {
		cartService.deleteCart("",2L);
	}

}
