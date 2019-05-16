package com.dq.shopprojectindex.paike;

import com.dq.shopprojectindex.factory.Dog;

/**
 * @author DuQian
 * @Date 2019/4/14
 */
public class Test2 {
    public static void main(String[] args) {
        Bird bird = new Bird();
        BirdDirect birdDirect = new BirdDirect();
        LastDirect lastDirect = new LastDirect();
        birdDirect.setAnimal(bird);
        lastDirect.setAnimal(birdDirect);
        lastDirect.eat();
    }
}
interface Animal{
    void eat();
}
class Bird implements Animal{
    @Override
    public void eat() {
        System.out.println("鸟吃饭");
    }
}
abstract class Direct implements Animal{
    protected Animal animal;
    void setAnimal(Animal animal){
        this.animal = animal;
    }
    public void eat(){
        animal.eat();
    }
}
class BirdDirect extends Direct{
    @Override
    public void eat() {
        reEat();
        super.eat();
    }

    private void reEat() {
        System.out.println("鸟先吃一顿");
    }
}
class LastDirect extends Direct{
    @Override
    public void eat() {
        super.eat();
        reEat();
    }

    private void reEat() {
        System.out.println("鸟最后一餐");
    }
}


