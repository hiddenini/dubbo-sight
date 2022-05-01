package com.xz.framework;

import java.util.List;
import java.util.Random;

public class LoadBalance {
    /**
     * 随机
     */
    public static URL random(List<URL> list) {
        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }
}
