package com.dq.shopprojectindex.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dq.common.constant.RabbitMQConstant;
import com.dq.common.service.IProductTypeService;
import com.project.entity.TProductType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author DuQian
 * @Date 2019/3/13
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @Reference
    private IProductTypeService productTypeService;

    @RequestMapping("list")
    public String list(Model model){
        List<TProductType> list = productTypeService.list();
        model.addAttribute("list",list);
        return "index";
    }
}
