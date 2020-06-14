package laba5;
import javafx.scene.shape.MoveTo;
import laba5.Exceptions.*;
import laba5.Interface.*;
import laba5.Model.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;

public class StaticsMethods {
    public static void PrintAllNames(Vehicle vehicle) {
        System.out.println("Массив названий моделей " + vehicle.getMark()+ " " +  Arrays.toString(vehicle.getModelNamesArray()));
    }
    public static void PrintAllPrices(Vehicle vehicle) {
        System.out.println("Массив цен моделей "+ vehicle.getMark()+ " " + Arrays.toString(vehicle.getModelPricesArray()));
    }

    public static double AveragePrices(Vehicle... vehicles) {
        double average = 0;
        double sum;
        int len;
        for (int i = 0; i < vehicles.length; i++) {
            len = vehicles[i].getSize();
            sum = 0;
            for (int j = 0; j < len; j++) {
                sum+=vehicles[i].getModelPricesArray()[j];
            }
            average += (sum / len);
        }
        return average / vehicles.length;
    }

    public static Vehicle createVehicle(String name, int size, Vehicle vehicle)  {
        Class clas = vehicle.getClass();
        try {
            Constructor constructor = clas.getConstructor(new Class[]{String.class, Integer.TYPE});

            // if (constructor == null) {
            //   return null;
            // }
            Vehicle reflectVehicle = (Vehicle) constructor.newInstance(name, size);
            return reflectVehicle;
        } catch (Exception ex) {
            return null;
        }
    }

    public static void writeVehicle(Vehicle vehicle) {
        int count = vehicle.getSize();
        System.out.printf("Марка: " + vehicle.getMark() + " ");
        System.out.printf("Количество моделей: " + count + " ");
        for (int i = 0; i < count; i++) {
            System.out.printf(" "+vehicle.getModelNamesArray()[i]+ ":");
            System.out.printf(" "+vehicle.getModelPricesArray()[i]+ " ");
        }
    }

    public static Vehicle readVehicle() throws DuplicateModelNameException {
        double Price; String Name;
        Vehicle Moto = new Motorcycle ();
        Scanner scaner = new Scanner(System.in);
        Moto.setMark(scaner.next());
        int size = scaner.nextInt();
        for (int i = 0; i < size; i++) {
            Name = scaner.next();
            Price = scaner.nextInt();
            Moto.addModel(Name,Price);
        }
        return Moto;
    }


    public static void writeVehicle(Vehicle v, Writer out){
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.println(v.getMark());
        printWriter.println(v.getSize());
        for (int i = 0; i < v.getSize(); i++) {
            String[] models = v.getModelNamesArray();
            double[] prices = v.getModelPricesArray();
            printWriter.println(models[i]);
            printWriter.println(prices[i]);
        }
    }
    public static Vehicle readVehicle(Reader reader) throws IOException, DuplicateModelNameException, NoSuchModelNameException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        Vehicle vehicle = new Motorcycle();
        String mark = bufferedReader.readLine();
        vehicle.setMark(mark);
        int size = Integer.valueOf(bufferedReader.readLine());
        for (int i = 0; i < size; i++) {
            vehicle.addModel(bufferedReader.readLine(), Double.valueOf(bufferedReader.readLine()));
        }
        return vehicle;
    }


    public static void outputVehicle(Vehicle v, OutputStream out) throws IOException {
        DataOutputStream dostream = new DataOutputStream(out);
        byte[] bytes;
        bytes = v.getMark().getBytes();
        dostream.writeInt(bytes.length);
        dostream.write(bytes);
        dostream.writeInt(v.getSize());
        for (int i = 0; i < v.getSize(); i++) {
            String[] models = v.getModelNamesArray();
            double[] prices = v.getModelPricesArray();
            bytes = models[i].getBytes();
            dostream.writeInt(bytes.length);
            dostream.write(bytes);
            dostream.writeDouble(prices[i]);
        }
    }
    public static Vehicle inputVehicle(InputStream in) throws IOException, DuplicateModelNameException, NoSuchModelNameException {
        Vehicle v = new Motorcycle();
        DataInputStream dataInputStream = new DataInputStream(in);
        byte[] bytes;
        int size = dataInputStream.readInt();
        bytes = new byte[size];
        dataInputStream.read(bytes);
        String marka = new String(bytes);
        v.setMark(marka);
        size = dataInputStream.readInt();
        int sizei;
        for (int i = 0; i < size; i++) {
            sizei = dataInputStream.readInt();
            bytes = new byte[sizei];
            dataInputStream.read(bytes);
            String model = new String(bytes);
            v.addModel(model, dataInputStream.readDouble());
        }
        return v;
    }
}
