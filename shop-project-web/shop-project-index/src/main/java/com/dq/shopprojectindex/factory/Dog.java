package com.dq.shopprojectindex.factory;

/**
 * @author DuQian
 * @Date 2019/4/8
 */
public class Dog extends AnimalFactory {
    public Dog(){}

    @Override
    public void show() {
        System.out.println("我是狗类");;
    }
}
