package laba5.Threads;

import laba5.Interface.Vehicle;

public class ShowMarkThread implements Runnable {
    private Vehicle vehicle;

    public ShowMarkThread(Vehicle v){
        vehicle = v;
    }

    @Override
    public void run(){
        System.out.println(vehicle.getMark());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
