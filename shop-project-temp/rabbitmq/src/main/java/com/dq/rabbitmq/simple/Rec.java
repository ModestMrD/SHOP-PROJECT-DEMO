package com.dq.rabbitmq.simple;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author DuQian
 * @Date 2019/3/18
 */
public class Rec {
    private final static String QUEUE_NAME = "simple";

    public static void main(String[] argv) throws Exception {
        //1.连接MQ服务器
        ConnectionFactory factory = new ConnectionFactory();
        factory.setVirtualHost("/java1809");
        factory.setUsername("dq");
        factory.setPassword("123");
        factory.setHost("192.168.48.129");
        factory.setPort(5672);
        //2.发送消息给MQ服务器
        Connection connection = factory.newConnection();
        //3.基于channel，类似会话的作用
        Channel channel = connection.createChannel();
        //4.声明消费者
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                //处理接收到的消息信息
                String msg = new String(body,"utf-8");
                System.out.println("接收到的消息为："+msg);
            }
        };
        //5.让消费者去监听队列
        //autoAck:为true即自动回复模式
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
