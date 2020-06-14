package laba5.Model;
import laba5.Exceptions.*;
import laba5.Interface.Vehicle;

import java.io.Serializable;
import java.util.*;

public class Quadrocycle implements Vehicle {
    private String mark;
    private ArrayList<Model> Models;

    public Quadrocycle(String Mark, int n){
        mark = Mark;
        Models = new ArrayList<>();
        for (int i = 0; i < n ; i++) {
            Models.add(new Model("Model"+(i+1),(double) i*i*i));
        }
    }
    private class Model implements Serializable, Cloneable {
        private String ModelName = null;
        private double ModelPrice = Double.NaN;
        public Model(String Name, double Price) {
            ModelName = Name;
            ModelPrice = Price;
        }
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
    public void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        int k = -1;
        for (int i = 0; i < Models.size() ; i++) {
            if(Models.get(i).ModelName.equals(oldName)) k = i;
            if (Models.get(i).ModelName.equals(newName)) throw new DuplicateModelNameException("Такое имя уже существует");
        }
        if(k!=-1) {
            double Price;
            Price = Models.get(k).ModelPrice;
            Models.remove(k);
            Models.add(k, new Model(newName, Price));
        }else throw new NoSuchModelNameException("Введенной модели не существует");
    }

    @Override
    public String[] getModelNamesArray() {
        String[] NamesArray = new String[Models.size()];
        for (int i = 0; i < Models.size(); i++) {
            NamesArray[i] = Models.get(i).ModelName;
        }
        return NamesArray;
    }

    @Override
    public double[] getModelPricesArray() {
        double[] PriceArray = new double[Models.size()];
        for (int i = 0; i < Models.size(); i++) {
            PriceArray[i] = Models.get(i).ModelPrice;
        }
        return PriceArray;
    }

    @Override
    public int getSize() {
        return Models.size();
    }

    @Override
    public double getPriceConcret(String Name) throws NoSuchModelNameException {
        int k = -1;
        for (int i = 0; i < Models.size() ; i++) {
            if(Models.get(i).ModelName.equals(Name)) {
                k = i;
                break;
            }
        }
        if(k!=-1) {
            return Models.get(k).ModelPrice;
        }else throw new NoSuchModelNameException("Введенной модели не существует");
    }

    @Override
    public void setConcretModelPrice(String Name, double Price) throws NoSuchModelNameException {
        if(Price < 0) throw new ModelPriceOutOfBoundsException("Введено неверное значение цены");
        int k = -1;
        for (int i = 0; i < Models.size() ; i++) {
            if(Models.get(i).ModelName.equals(Name)) {
                k = i;
                break;
            }
        }
        if(k!=-1) {
            Models.remove(k);
            Models.add(k, new Model(Name, Price));
        }else throw new NoSuchModelNameException("Введенной модели не существует");
    }

    @Override
    public void addModel(String Name, double Price) throws DuplicateModelNameException {
        if(Price < 0) throw new ModelPriceOutOfBoundsException("Введено неверное значение цены");
        for (int i = 0; i < Models.size() ; i++) {
            if (Models.get(i).ModelName.equals(Name)) throw new DuplicateModelNameException("Такое имя уже существует");
        }
        Models.add(new Model(Name,Price));
    }

    @Override
    public void removeModel(String Name) throws NoSuchModelNameException {
        int k = -1;
        for (int i = 0; i < Models.size() ; i++) {
            if(Models.get(i).ModelName.equals(Name)) {
                k = i;
                break;
            }
        }
        if(k!=-1) {
            Models.remove(k);
        }else throw new NoSuchModelNameException("Введенной модели не существует");
    }
}
