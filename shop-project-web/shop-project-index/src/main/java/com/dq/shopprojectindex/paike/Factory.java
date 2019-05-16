package com.dq.shopprojectindex.paike;

/**
 * @author DuQian
 * @Date 2019/4/14
 */
public class Factory {
    public static int MAN = 1;
    public static int WOMAN = 2;
    public static Human getInstance(int type){
        if (type == MAN){
            return new Man();
        }
        if (type == WOMAN){
            return new Women();
        }
        return null;
    }
}
interface Human{
    void say();
}
class Women implements Human{
    @Override
    public void say() {
        System.out.println("i am women");
    }
}
class Man implements Human{
    @Override
    public void say() {
        System.out.println("i am man");
    }
}
class Test{
    public static void main(String[] args) {
        Human human1 = Factory.getInstance(Factory.MAN);
        Human human2 = Factory.getInstance(Factory.WOMAN);
        human1.say();
        human2.say();
    }
}