package com.example.thread.lock;

/**
 * <p>
 *      这里没有产生死锁的原因：
 *      setup.1 : Child继承了Super
 *      setup.2 : main 方法中创建了子类的实例
 *      setup.3 : 当调用子类的 doSomething() 会获取锁，因为是实例方法，
 *                所以这里获取到的是new出来的子类实例，子类方法中又调用
 *                了父类的同步方法，父类此时此刻获取到的锁就是子类的锁，
 *                所以可以进入该方法
 *      setup.4 : super 关键字理解：很显然子类中输出super.toString() 就
 *                是子类本身，所以通过super.doSomething()传入的锁，就是
 *                子类的实例
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class DidLock {

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(
                    () -> {
                        Child child = new Child();
                        child.doSomething();
                    }
            ).start();
        }
    }

}

class Supper {
    public synchronized void doSomething(){
        System.out.println(toString() + "super doSomething");
    }
}

class Child extends Supper {
    @Override
    public synchronized void doSomething() {
        System.out.println(toString() + "call super doSomething");
        super.doSomething();
        System.out.println("super：" + super.toString());
    }
}