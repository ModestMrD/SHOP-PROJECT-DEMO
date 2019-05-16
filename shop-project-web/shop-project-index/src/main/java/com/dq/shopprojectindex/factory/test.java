package com.dq.shopprojectindex.factory;

/**
 * @author DuQian
 * @Date 2019/4/8
 */
public class test {
    public static void main(String[] args) {
        AnimalFactory instance = AnimalFactory.getInstance(AnimalFactory.PIG_INSTANCE);
        instance.show();
    }
}
