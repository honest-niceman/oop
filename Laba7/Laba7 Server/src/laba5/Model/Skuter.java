package laba5.Model;
import laba5.Exceptions.*;
import laba5.Interface.Vehicle;
import java.util.*;

public class Skuter implements Vehicle {

    private String mark;
    private HashMap<String, Double> ModelsAndPrices;
    public Skuter(String Mark, int n){
        mark = Mark;
        ModelsAndPrices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            ModelsAndPrices.put("Model"+(i+1), (double) (i*i*i));
        }
    }
    @Override
    public double[] getModelPricesArray() {
        double[] array = new double[ModelsAndPrices.size()];
        int i = 0;
        for (double value : ModelsAndPrices.values()) {
            array[i] = value;
            i++;
        }
        return array;
    }
    @Override
    public String getMark() {
        return mark;
    }
    @Override
    public void setMark(String Mark) {
        mark = Mark;
    }
    @Override
    public int getSize() {
        return ModelsAndPrices.size();
    }
    @Override
    public void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        if(ModelsAndPrices.get(oldName)!=null)
        {
            double Price = ModelsAndPrices.get(oldName);
            for (String value : ModelsAndPrices.keySet()) {
                if (value.equals(newName)) throw new DuplicateModelNameException("Такое имя уже существует");
            }
            ModelsAndPrices.remove(oldName, Price);
            ModelsAndPrices.put(newName, Price);
        }
        else throw new NoSuchModelNameException("Введенной модели не существует");
    }
    @Override
    public double getPriceConcret(String Name) throws NoSuchModelNameException {
        if(ModelsAndPrices.get(Name)!=null) {
            return ModelsAndPrices.get(Name);
        }
        else throw new NoSuchModelNameException("Введенной модели не существует");
    }

    @Override
    public void setConcretModelPrice(String Name, double Price) throws NoSuchModelNameException {
        if(Price < 0) throw new ModelPriceOutOfBoundsException("Введено неверное значение цены");
        if(ModelsAndPrices.get(Name)!=null) {
            ModelsAndPrices.replace(Name,Price);
        }
        else throw new NoSuchModelNameException("Введенной модели не существует");
    }

    @Override
    public void addModel(String Name, double Price) throws DuplicateModelNameException {
        if(Price < 0) throw new ModelPriceOutOfBoundsException("Введено неверное значение цены");
        for (String value : ModelsAndPrices.keySet()) {
            if (value.equals(Name)) throw new DuplicateModelNameException("Такое имя уже существует");
        }
        ModelsAndPrices.put(Name, Price);
    }

    @Override
    public void removeModel(String Name) throws NoSuchModelNameException {
        if(ModelsAndPrices.get(Name)!=null) {
            ModelsAndPrices.remove(Name);
        }
        else throw new NoSuchModelNameException("Введенной модели не существует");
    }
    @Override
    public String[] getModelNamesArray() {
        String[] array = new String[ModelsAndPrices.size()];
        int i = 0;
        for (String value : ModelsAndPrices.keySet()) {
            array[i] = value;
            i++;
        }
        return array;
    }
}
