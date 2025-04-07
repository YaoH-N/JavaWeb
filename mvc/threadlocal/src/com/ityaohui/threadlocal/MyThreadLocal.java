package com.ityaohui.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: 小牛
 * Date: 2025/4/7 16:57
 * Description: 自定一的ThreadLocal 所有需要和当前线程绑定的数据都放到这个容器当中
 */
public class MyThreadLocal<T> {
    private Map<Thread, T> map = new HashMap<>();

    /**
     * 向ThreadLocal当中存
     */
    public void set(T obj) {
        map.put(Thread.currentThread(), obj);
    }

    public T get() {
        return map.get(Thread.currentThread());
    }

    public void remove() {
        map.remove(Thread.currentThread());
    }
}
