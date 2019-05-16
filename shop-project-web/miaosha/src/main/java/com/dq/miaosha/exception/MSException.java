package com.dq.miaosha.exception;

/**
 * @author DuQian
 * @Date 2019/3/29
 * 自定义秒杀异常
 */
public class MSException extends RuntimeException{
    public MSException(String msg){
        super(msg);
    }
}
