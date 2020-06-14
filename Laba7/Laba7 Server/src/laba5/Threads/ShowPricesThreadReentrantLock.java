package laba5.Threads;

import laba5.Interface.Vehicle;

import java.util.concurrent.locks.ReentrantLock;

public class ShowPricesThreadReentrantLock implements Runnable {

    private ReentrantLock reentrantLock;
    private Vehicle vehicle;

    public ShowPricesThreadReentrantLock(ReentrantLock ts, Vehicle v){
        reentrantLock = ts;
        vehicle = v;
    }

    @Override
    public void run() {
        try {
            reentrantLock.lock();
            for (int i = 0; i < vehicle.getSize(); i++) {
                System.out.println(vehicle.getModelPricesArray()[i]);
            }
        } finally {
            reentrantLock.unlock();
        }
    }
}
