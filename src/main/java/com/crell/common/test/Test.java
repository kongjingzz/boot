package com.crell.common.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by crell on 17/2/8.
 */
public class Test {
    public static void main(String[] args){
        ExecutorService exe = Executors.newFixedThreadPool(50);
        for (int i = 1; i <= 5; i++) {
            exe.execute(new SubThread(i));
        }
        exe.shutdown();
        while (true) {
            if (exe.isTerminated()) {
                System.out.println("结束了！");
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
