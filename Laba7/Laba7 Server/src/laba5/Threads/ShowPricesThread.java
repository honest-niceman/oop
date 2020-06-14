package laba5.Threads;
import laba5.Interface.Vehicle;

import javax.swing.*;

public class ShowPricesThread extends Thread {
    private Vehicle vehicle;

    public ShowPricesThread(Vehicle v){
        vehicle = v;
    }

    @Override
    public void run(){
        for (int i = 0; i < vehicle.getSize(); i++) {
            System.out.println(vehicle.getModelPricesArray()[i]);
        }
    }
}
