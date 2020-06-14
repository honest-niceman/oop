package laba5;
import laba5.Interface.Vehicle;
import laba5.Threads.ThreadForNet;
import java.io.*;
import java.net.*;

public class Test {
    public static void main(String[] argv) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(4444)) {
            System.out.println("Задание№2");
            System.out.println("Сервер (в рамках модели последовательной обработки запросов) запущен и ожиданиет запросов клиентов");
            int i = 0;
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ThreadForNet(clientSocket).start();
            }
        }
    }
}
