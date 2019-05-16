package com.dq.shop.direct;

/**
 * @author DuQian
 * @Date 2019/4/15
 */
public class Zsz {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Pre pre = new Pre();
        Aft aft = new Aft();
        pre.setInstance(dog);
        aft.setInstance(pre);
        aft.show();
    }
}
interface Animal{
    void show();
}
class Dog implements Animal{
    @Override
    public void show() {
        System.out.println("狗吃饭");
    }
}
class Direct implements Animal{
    public Animal animal;
    public void setInstance(Animal animal){
        this.animal = animal;
    }
    @Override
    public void show() {
        animal.show();
    }
}
class Pre extends Direct{
    @Override
    public void show() {
        reShow();
        super.show();
    }

    public void reShow() {
        System.out.println("狗先吃饭");
    }
}
class Aft extends Direct{
    @Override
    public void show() {
        super.show();
        reShow();
    }
    public void reShow() {
        System.out.println("狗后吃饭");
    }
}
