package com.zhaofan.more_thread_execute;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo implements TestDemo{
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    private int x = 0;
    private void count(){
        writeLock.lock();
        try {
            x++;
        }finally {
            writeLock.unlock();
        }
    }

    private void print(int time){
        readLock.lock();
        try{
            for (int i=0;i<time;i++){
                System.out.println(x + " ");
            }
        }finally {
            readLock.unlock();
        }
    }

    @Override
    public void runTest() {

    }
}
