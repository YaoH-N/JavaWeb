package com.ityaohui.template1;

/**
 * Author: 小牛
 * Date: 2025/3/10 23:13
 * Description:
 */
public class Teacher {
    /**
     * 老师的一天
     */
    public void day() {
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

    public void doSome() {
        System.out.println("老师授课");
    }

    public void chiWanFan() {
        System.out.println("吃晚饭");
    }

    public void shuiJiao() {
        System.out.println("睡觉");
    }
}
