package laba5.Threads;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowPricesThreadRunnable implements Runnable {

    private TransportSynchronizer transportSynchronizer;

    public ShowPricesThreadRunnable(TransportSynchronizer TS) {
        transportSynchronizer = TS;
    }

    @Override
    public void run() {
        try {
            while (transportSynchronizer.canPrintPrice()) {
                transportSynchronizer.printPrice();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
