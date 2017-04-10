package com.crell.common.test;

/**
 * Created by crell on 17/2/8.
 */
public class WaitNotifyDemo {
    public static void main(String[] args) {

        final Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    business.sub(i);
                }

            }
        }).start();

        for (int i = 1; i <= 5; i++) {
            business.main(i);
        }
    }
}

class Business {
    private boolean isMainThread = true;

    public synchronized void sub(int i) {
        while (!isMainThread) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 2; j++) {
            System.out.println("sub thread sequence of " + j + ",loop of " + i);
        }
        isMainThread = false;
        this.notify();
    }

    public synchronized void main(int i) {
        while (isMainThread) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 10; j++) {
            System.out.println("main thread sequence of " + j + ",loop of " + i);
        }
        isMainThread = true;
        this.notify();
    }
}
