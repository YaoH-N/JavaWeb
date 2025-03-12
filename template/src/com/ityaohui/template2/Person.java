package com.ityaohui.template2;

/**
 * Author: 小牛
 * Date: 2025/3/10 23:19
 * Description:
 * 1. Person就是模板方法设计模式中的模板类
 * 2. day()方法就是模板方法设计模式当中的模板方法
 * 模板类通常是抽象类
 */
public abstract class Person {

    // 模板方法
    // 添加了final之后，这个方法无法被覆盖，这样核心的算法也可以得到保护
    // 模板方法定义和新的算法骨架，具体的实现步骤可以延迟到子类中去实现
    // 核心算法一方面得到了保护，不能被改变，另一方面就是算法得到了重复使用
    // 另外代码也得到了复用，因为算法中的某些步骤的代码是固定的，这些固定的代码不会随着子类的变化而变化，这一部分代码可以写到模板类中
    public final void day() {
        qiChuang();
        xiShu();
        chiZaoCan();
        doSome();
        chiWanFan();
        shuiJiao();
    }

    public void qiChuang() {
        System.out.println("起床");
    }

    public void xiShu() {
        System.out.println("洗漱");
    }

    public void chiZaoCan() {
        System.out.println("吃早餐");
    }

    public abstract void doSome();

    public void chiWanFan() {
        System.out.println("吃晚饭");
    }

    public void shuiJiao() {
        System.out.println("睡觉");
    }
}
