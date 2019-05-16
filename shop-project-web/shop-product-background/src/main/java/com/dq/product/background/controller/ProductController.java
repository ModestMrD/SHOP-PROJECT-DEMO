package com.dq.product.background.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dq.common.constant.RabbitMQConstant;
import com.dq.common.service.IProductService;
import com.dq.common.vo.ProductVO;
import com.github.pagehelper.PageInfo;
import com.project.entity.TProduct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("product")
public class ProductController {

    @Reference
    private IProductService productService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("page/{pageIndex}/{pageSize}")
    public String page(Model model, @PathVariable Integer pageSize, @PathVariable Integer pageIndex){
        PageInfo<TProduct> pageInfo = productService.page(pageSize,pageIndex);
        model.addAttribute("pageInfo",pageInfo);
        return "product/list";
    }

    @RequestMapping("add")
    public String add(ProductVO productVO){
        TProduct product = productService.add(productVO);
        //发送消息到交换机即可
        rabbitTemplate.convertAndSend(RabbitMQConstant.BACKGROUND_EXCHANGE,"search.updateData",product);
        rabbitTemplate.convertAndSend(RabbitMQConstant.BACKGROUND_EXCHANGE,"item.createHtml",product);
        return "redirect:page/1/5";
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(Long id){
        productService.deleteByPrimaryKey(id);
        return "ok";
    }
}
