package laba5.Threads;

import laba5.Interface.Vehicle;

public class ShowNamesThread extends Thread {
    private Vehicle vehicle;

    public ShowNamesThread(Vehicle v){
        vehicle = v;
    }

    @Override
    public void run(){
        for (int i = 0; i < vehicle.getSize(); i++) {
            System.out.println(vehicle.getModelNamesArray()[i]);
        }
    }
}
