package laba5.Threads;

import laba5.Interface.Vehicle;
import java.util.concurrent.locks.ReentrantLock;

public class ShowNamesThreadReentrantLock implements Runnable{

    private ReentrantLock reentrantLock;
    private Vehicle vehicle;

    public ShowNamesThreadReentrantLock(ReentrantLock ts, Vehicle v){
        reentrantLock = ts;
        vehicle = v;
    }

    @Override
    public void run() {
        try {
            reentrantLock.lock();
            for (int i = 0; i < vehicle.getSize(); i++) {
                System.out.println(vehicle.getModelNamesArray()[i]);
            }
        } finally {
            reentrantLock.unlock();
        }
    }
}
