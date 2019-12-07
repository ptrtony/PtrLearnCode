package com.zhaofan.more_thread_execute;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class Main {

    public static void main(String[] args) {
        runnable();
        threadFactory();
        executor();
        callable();
        runSynchronized1Demo();
    }
    static void runnable(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("thread is runnable start!");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    static void threadFactory(){
        ThreadFactory threadFactory = new ThreadFactory() {
            int count = 0;
            @Override
            public Thread newThread(Runnable r) {
                count++;
                return new Thread(r,"thread-"+count);
            }
        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"start!");
            }
        };
        Thread thread1 = threadFactory.newThread(runnable);
        thread1.start();
        Thread thread2 = threadFactory.newThread(runnable);
        thread2.start();
    }

    static void executor(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("thread with runnable start!");
            }
        };

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(runnable);
        executor.execute(runnable);
        executor.execute(runnable);
    }

    //可以返回的runnable
    static void callable(){
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                return "Done";
            }
        };

        ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> future = executor.submit(callable);
        try {
            while (!future.isDone()){

            }
            String result = future.get();
            System.out.println("result:"+result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void runSynchronized1Demo(){
        new Synchronized1Demo().runTest();
    }

    static void runSynchronized2Demo(){
        new Synchronized2Demo().runTest();
    }

    static void runSynchronzied3Demo(){
        new Synchronized3Demo().runTest();
    }




}
