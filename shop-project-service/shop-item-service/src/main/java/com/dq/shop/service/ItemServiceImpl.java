package com.dq.shop.service;

import com.alibaba.dubbo.common.threadpool.support.fixed.FixedThreadPool;
import com.alibaba.dubbo.config.annotation.Service;
import com.dq.common.POJO.ResultBean;
import com.dq.shop.ItemService;
import com.project.entity.TProduct;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author DuQian
 * @Date 2019/3/15
 */
@Service
@Component
public class ItemServiceImpl implements ItemService {

    @Autowired
    private Configuration configuration;

    //自定义线程池
    private int poolSize = Runtime.getRuntime().availableProcessors();
    private ExecutorService pool = new ThreadPoolExecutor(
            poolSize,poolSize*2,
            0L, TimeUnit.SECONDS,new LinkedBlockingQueue(100));
    @Override
    public ResultBean createHtml(TProduct product) {
        try {
            Template template = configuration.getTemplate("item.ftl");
            Map<String,Object> data = new HashMap<>();
            data.put("product",product);
            FileWriter writer = new FileWriter("D:\\html\\"+product.getId()+".html");
            template.process(data,writer);
        } catch (Exception e) {
            e.printStackTrace();
            ResultBean.error("生成静态html失败");
        }
        return ResultBean.success("生成静态html成功");
    }

    @Override
    public ResultBean batchHtml(TProduct[] products) {
        try {
            for (int i = 0; i < products.length; i++) {
                pool.submit(new BatchCreateHtmlTask(products[i]));
            }
        }catch (Exception e){
            ResultBean.error("生成静态html失败");
        }
        return ResultBean.success("生成静态html成功");
    }
    private  class BatchCreateHtmlTask implements Callable<Boolean>{

        private TProduct product;

        public BatchCreateHtmlTask(TProduct product){
            this.product = product;
        }
        @Override
        public Boolean call() throws Exception {
            Template template = configuration.getTemplate("item.ftl");
            Map<String,Object> data = new HashMap<>();
            data.put("product",product);
            FileWriter writer = new FileWriter("D:\\html\\"+product.getId()+".html");
            template.process(data,writer);
            return true;
        }
    }
}
