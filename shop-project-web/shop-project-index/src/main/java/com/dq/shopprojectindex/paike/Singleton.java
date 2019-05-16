package com.dq.shopprojectindex.paike;

/**
 * @author DuQian
 * @Date 2019/4/14
 */
public class Singleton {
    private static Singleton singleton = new Singleton();
    private Singleton(){}
    public static Singleton getInstance(){ return singleton;}
}
class Singleton2{
    private static Singleton2 singleton2 = null;
    private Singleton2(){}
    public synchronized static Singleton2 getInstance(){
        if (singleton2==null)
        {
            return singleton2 = new Singleton2();
        }
        return singleton2;
    }
}
class test{
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1==singleton2);
        Singleton2 singleton3 = Singleton2.getInstance();
        Singleton2 singleton4 = Singleton2.getInstance();
        System.out.println(singleton3==singleton4);

    }
}
