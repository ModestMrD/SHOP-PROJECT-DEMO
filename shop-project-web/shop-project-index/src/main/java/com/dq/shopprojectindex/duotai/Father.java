package com.dq.shopprojectindex.duotai;

/**
 * @author DuQian
 * @Date 2019/4/8
 */
public abstract class Father {
    public void show(){};
}
class Son1 extends Father{
    @Override
    public void show() {
        System.out.println("第一个儿子");;
    }
}
class Son2 extends Father{
    @Override
    public void show() {
        System.out.println("第二个儿子");;
    }
}
class test{
    int a = 3;
    public static void main(String[] args) {
        double b = 3/2;
        System.out.println(b);
    }
}
