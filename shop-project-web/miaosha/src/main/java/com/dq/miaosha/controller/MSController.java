package com.dq.miaosha.controller;

import com.dq.miaosha.entity.TProduct;
import com.dq.miaosha.exception.MSException;
import com.dq.miaosha.pojo.ResultBean;
import com.dq.miaosha.service.IProductService;
import com.dq.miaosha.service.ISecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author DuQian
 * @Date 2019/3/29
 */
@Controller
@RequestMapping("ms")
public class MSController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ISecKillService seckillService;

    @RequestMapping("getById")
    public String getById(Long id, Model model){
        TProduct product = productService.getById(id);
        model.addAttribute("product",product);
        return "item";
    }

    @RequestMapping("saleById")
    @ResponseBody
    public String saleById(Long id){
        boolean result = productService.saleById(id);//1
        if(result){//
            return "success";
        }
        return "faild";
    }

    @RequestMapping("seckill")
    @ResponseBody
    public ResultBean seckill(Long seckillId,Long userId){
        try {
            seckillService.seckill(seckillId,userId);
            return ResultBean.success("秒杀成功！");
        }catch(MSException ms){
            return ResultBean.error(ms.getMessage());
        }
    }


}
