package com.dq.shopprojectindex.factory;

/**
 * @author DuQian
 * @Date 2019/4/8
 */
public class Pig extends AnimalFactory {
    public Pig(){
    }

    @Override
    public void show() {
        System.out.println("我是猪类");
    }
}
