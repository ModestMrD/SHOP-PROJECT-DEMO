package com.dq.shopprojectindex.factory;

/**
 * @author DuQian
 * @Date 2019/4/8
 */
public class AnimalFactory {
    public static final int PIG_INSTANCE = 1;
    public static final int DOG_INSTANCE = 2;

    public static AnimalFactory getInstance(int i){
        if (i==PIG_INSTANCE){
            return new Pig();
        }else if (i==DOG_INSTANCE){
            return new Dog();
        }
        return null;
    }
    public void show(){
        System.out.println("我是工厂");
    }
}
