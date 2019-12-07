package com.zhaofan.more_thread_execute;

public class Synchronized3Demo implements TestDemo{
    private int x = 0;
    private int y = 0;
    private String name;
    private synchronized void count(int newValue){
        x = newValue;
        y = newValue;
    }

    private synchronized void setName(String newName){
        name = newName;
    }

    private synchronized void minus(int delta){
        x -= delta;
        y -= delta;
    }

    @Override
    public void runTest() {

    }
}
