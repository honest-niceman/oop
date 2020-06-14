package laba5.Threads;
import laba5.Interface.Vehicle;
import laba5.StaticsMethods;
import java.io.*;
import java.net.*;

public class ThreadForNet extends Thread {
    private Socket clientSocket;

    public ThreadForNet(Socket s) {
        clientSocket = s;
    }

    @Override
    public void run() {
        try {
            System.out.println("Клиент подключился. Считаем среднее арифметическое значение цен моделей...");

            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            Vehicle[] vehicles = (Vehicle[]) objectInputStream.readObject();

            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            dataOutputStream.writeDouble(StaticsMethods.AveragePrices(vehicles));
            dataOutputStream.flush();

            System.out.println("Значение передано клиенту ");
            System.out.println("");
            clientSocket.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
