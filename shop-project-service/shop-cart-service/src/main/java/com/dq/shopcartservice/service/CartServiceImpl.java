package com.dq.shopcartservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dq.common.POJO.ResultBean;
import com.dq.pojo.CartItem;
import com.dq.project.mapper.TProductMapper;
import com.dq.service.ICartService;
import com.dq.vo.CartItemVO;
import com.project.entity.TProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author DuQian
 * @Date 2019/3/25
 */
@Service
@Component
public class CartServiceImpl implements ICartService{

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TProductMapper productMapper;

    @Override
    public ResultBean addCart(String key, Long productId, Integer count) {
        List<CartItem> list = (List<CartItem>) redisTemplate.opsForValue().get(key);//获取该用户购物车
        if (list == null){//如果购物车为空
            list = new ArrayList<>();//实例化一个购物车
            list.add(new CartItem(productId,count,new Date()));//将商品加入购物车
            flushRedis(key, list ,30*24*60);
            return ResultBean.success("加入购物车成功！");
        }
        for (CartItem cartItem : list) {//遍历集合，看该商品是否已存在
            if (cartItem.getProductId().longValue()==productId.longValue()){//已存在
                cartItem.setCount(cartItem.getCount()+count);//更新数量
                cartItem.setUpdateDate(new Date());//更新更新时间
                flushRedis(key, list ,30*24*60);
                return ResultBean.success("加入购物车成功！");
            }
        }
        list.add(new CartItem(productId,count,new Date()));//将商品加入购物车
        flushRedis(key, list ,30*24*60);
        return ResultBean.success("加入购物车成功！");
    }

    @Override
    public ResultBean updateCart(String key, Long productId, Integer count) {
        List<CartItem> list = (List<CartItem>) redisTemplate.opsForValue().get(key);
        if (list==null||"".equals(list)){
            return ResultBean.error("更新失败");
        }
        for (CartItem cartItem : list) {
            if (productId.longValue()==cartItem.getProductId().longValue()){
                cartItem.setCount(count);
                break;
            }
        }
        flushRedis(key,list,30*24*60);//缓存30天
        return ResultBean.success("更新成功！");
    }

    @Override
    public ResultBean deleteCart(String key, Long productId) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        List<CartItem> list = (List<CartItem>) redisTemplate.opsForValue().get(key);
        if (list == null || "".equals(list)){
            return ResultBean.error("删除异常！");
        }
        for (CartItem cartItem : list) {
            if (cartItem.getProductId().longValue() == productId){
                list.remove(cartItem);
                break;
            }
        }
        flushRedis(key,list,30);
        String productKey = new StringBuilder("product:").append(productId).toString();
        redisTemplate.delete(productKey);
        return ResultBean.success("删除成功！");
    }

    @Override
    public ResultBean cartList(String key) {
        List<CartItem> list = (List<CartItem>) redisTemplate.opsForValue().get(key);
        if (list == null){
            return ResultBean.error("购物车空空如也~");
        }
        List<CartItemVO> cartItemVOList = new ArrayList<>(list.size());
        for (CartItem cartItem : list) {
            //系统提前做好热门商品的预热，根据商品的访问量，将20%的商品进行缓存
            String productKey = new StringBuilder("product:").append(cartItem.getProductId()).toString();
            TProduct product = (TProduct) redisTemplate.opsForValue().get(productKey);
            if (product == null){//如果缓存中没有该商品
                Long productId = cartItem.getProductId();
                product = productMapper.selectByPrimaryKey(productId);//通过商品id查询商品
            }
            //cartItemVOList集合中添加元素
            cartItemVOList.add(new CartItemVO(product,cartItem.getCount(),cartItem.getUpdateDate()));
            flushRedis(productKey,product,20);//缓存20分钟
        }
        //将cartItemVOList集合存入ResultBean，前端展示
        return new ResultBean(200,cartItemVOList);
    }

    private void flushRedis(String key, Object obj, Integer time) {
        redisTemplate.opsForValue().set(key,obj);//存入Redis
        redisTemplate.expire(key,time, TimeUnit.DAYS);//购物车缓存时间
    }
}
