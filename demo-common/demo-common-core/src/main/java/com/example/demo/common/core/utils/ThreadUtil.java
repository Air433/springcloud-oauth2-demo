package com.example.demo.common.core.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author ouyanggang
 * @Date 2019/6/17 - 20:43
 */
public class ThreadUtil {

    private static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    public static void singThreadSumit(Runnable runnable){
        singleThreadExecutor.execute(runnable);
    }
}
