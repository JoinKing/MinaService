package com.king.MinaService.utils;

public class TimePicker implements Runnable {
    private boolean isOver;

    @Override
    public void run() {
        try {
            isOver = false;
            Thread.sleep(1000*60*10);
            isOver= true;
            if (null!=callback){
                callback.over(isOver);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private OverCallback callback;

    public void setCallback(OverCallback callback) {
        this.callback = callback;
    }

    public interface OverCallback{
        void over(boolean isOver);


    }
}
