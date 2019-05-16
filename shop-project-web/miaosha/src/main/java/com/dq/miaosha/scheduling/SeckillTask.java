package com.dq.miaosha.scheduling;

import com.dq.miaosha.constant.Constant;
import com.dq.miaosha.entity.TSeckill;
import com.dq.miaosha.mapper.TSeckillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author HuangGuiZhao
 * @Date 2019/3/29
 * 1,自动扫描秒杀活动表，根据开始时间，开启秒杀活动
 * 2,自动扫描秒杀活动表，根据结束时间，关闭秒杀活动
 */
@Component
public class SeckillTask {

    @Autowired
    private TSeckillMapper seckillMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Scheduled(cron = "0/10 * * * * ?")//10秒扫描一次
    public void startSeckills(){
        //1.查看可以开启的秒杀活动
        List<TSeckill> list = seckillMapper.getCanStartSeckills();
        //2.更新状态。0-1
        for (TSeckill seckill : list) {
            //秒杀活动已开启
            seckill.setStatus("1");
            seckillMapper.updateSeckill(seckill);
            //3.redis创建List，用于保存本次秒杀活动包含的商品信息
            for (Integer integer = 0; integer < seckill.getCount(); integer++) {
                String key = new StringBuilder(
                        Constant.SECKILL_COUNT_PRE).append(seckill.getId()).toString();
                redisTemplate.opsForList().leftPush(key,seckill.getProductId());
            }
            //TODO 保存当前的活动信息至redis中
            String seckillKey = new StringBuilder(
                    Constant.SECKILL_PRE).append(seckill.getId()).toString();
            redisTemplate.opsForValue().set(seckillKey,seckill);
        }
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void stopSeckills(){
        //1.查看可以关闭的秒杀活动
        List<TSeckill> list = seckillMapper.getCanStopSeckills();
        //2.更新状态。1-2
        for (TSeckill seckill : list) {
            seckill.setStatus("2");
            seckillMapper.updateSeckill(seckill);
            //3.redis删除List
            String key = new StringBuilder(
                    Constant.SECKILL_COUNT_PRE).append(seckill.getId()).toString();
            redisTemplate.delete(key);
            //TODO 删除redis保存的活动信息
            String seckillKey = new StringBuilder(
                    Constant.SECKILL_PRE).append(seckill.getId()).toString();
            redisTemplate.delete(seckillKey);
        }
    }

}
