package laba5.Threads;


public class ShowNamesThreadRunnable implements Runnable{
    private TransportSynchronizer transportSynchronizer;

    public ShowNamesThreadRunnable(TransportSynchronizer TS) {
        transportSynchronizer = TS;
    }

    @Override
    public void run() {
        try {
            while (transportSynchronizer.canPrintPrice()) {
                transportSynchronizer.printModel();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
