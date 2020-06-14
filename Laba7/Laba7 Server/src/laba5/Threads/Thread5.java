package laba5.Threads;

import laba5.Interface.Vehicle;
import laba5.StaticsMethods;
import java.io.FileReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Thread5 implements Runnable {

    private String filename;
    private ArrayBlockingQueue<Vehicle> arrayBlockingQueue;

    public Thread5(String f, ArrayBlockingQueue abq)  {
        filename = f;
        arrayBlockingQueue = abq;
    }

    @Override
    public void run() {
        FileReader fileReader;
        try {
            fileReader = new FileReader(filename);
            Vehicle veh = StaticsMethods.readVehicle(fileReader);
            arrayBlockingQueue.put(veh);
            System.out.println(veh.getMark());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
