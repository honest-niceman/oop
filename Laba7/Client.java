package laba5;
import laba5.Interface.*;
import laba5.Model.*;
import java.io.*;
import java.net.*;

public class Test {
    public static void main(String[] argv) throws IOException {
        Vehicle Veh1 = new Moped("BMW", 50);
        Vehicle Veh2 = new Moped("AUDI", 55);
        Vehicle Veh3 = new Moped("MERCEDES", 500);
        Vehicle Veh4 = new Moped("BUGATTI", 20);
        Vehicle Veh5 = new Moped("NISSAN", 50);
        Vehicle[] vehicles = new Vehicle[]{Veh1,Veh2,Veh3,Veh4,Veh5};
        Socket socket = null;
        ObjectOutputStream objectOutputStream = null;
        DataInputStream dataInputStream = null;
        try {
            socket = new Socket("localhost", 4444);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(new DataInputStream(socket.getInputStream()));

            objectOutputStream.writeObject(vehicles);
            objectOutputStream.flush();

            System.out.println("Средняя цена транспортных средств = " + dataInputStream.readDouble());

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to:" + "localhost.");
            System.exit(1);
        }finally {
            objectOutputStream.close();
            dataInputStream.close();
            socket.close();
        }
    }
}
