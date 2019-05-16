package com.dq.shop.handler;

import com.dq.common.constant.RabbitMQConstant;
import com.dq.shop.ItemService;
import com.project.entity.TProduct;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author HuangGuiZhao
 * @Date 2019/3/18
 */
@Component
public class Consumer {

    @Autowired
    private ItemService itemService;

    @RabbitListener(queues = RabbitMQConstant.BACKGROUND_ITEM_SAVE_UPDATE_QUEUE)
    public void processAddOrUpdate(TProduct product){
        itemService.createHtml(product);
    }
}
