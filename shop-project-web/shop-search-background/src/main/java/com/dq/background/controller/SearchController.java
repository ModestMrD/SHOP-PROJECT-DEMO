package com.dq.background.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dq.common.POJO.PageInfo;
import com.dq.common.POJO.ResultBean;
import com.dq.common.constant.RabbitMQConstant;
import com.dq.shop.ISearchService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author DuQian
 * @Date 2019/3/14
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Reference
    private ISearchService searchService;

    @RequestMapping("initAllData")
    @ResponseBody
    public ResultBean initAllData(){
        return searchService.initAllData();
    }

    @RequestMapping("searchByKeyWord")
    public String searchByKeyWord(String keyWord,Integer indexNum,Integer pageSize, Model model){
        PageInfo pageInfo = searchService.page(keyWord,indexNum,pageSize);
        model.addAttribute("pageInfo",pageInfo);
        return "search";
    }
}
