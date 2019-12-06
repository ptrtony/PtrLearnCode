package com.zhaofan.inter_thread_communication;

public class ThreadInteractionDemo implements TestDemo {
    @Override
    public void runTest() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //

                }
                for (int i = 0; i < 1_000_000; i++) {
                    if (isInterrupted())
                    System.out.println("number:" + i);
                }
            }
        };
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();
        thread.interrupt();
    }
}
