package com.dq.shop.handler;

import com.dq.common.constant.RabbitMQConstant;
import com.dq.shop.ISearchService;
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
    private ISearchService searchService;

    @RabbitListener(queues = RabbitMQConstant.BACKGROUND_SEARCH_SAVE_UPDATE_QUEUE)
    public void processAddOrUpdate(TProduct product){
        searchService.updateData(product);
    }
}
