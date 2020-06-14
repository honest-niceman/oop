package laba5.Model;
import laba5.Exceptions.*;
import laba5.Interface.*;
import java.io.Serializable;
import java.util.Arrays;

public class Auto implements Vehicle, Cloneable {
    private String mark;
    private Model[] ModelArray;

    public Auto(String Mark, int n) {
        mark = Mark;
        ModelArray = new Model[n];
        for (int i = 0; i < n; i++)
            ModelArray[i] = new Model("Model" + (i + 1), i * i * i);
    }

    public Auto() { }

    @Override
    public String getMark() {
        return mark;
    }

    @Override
    public void setMark(String newMark) {
        mark = newMark;
    }

    private class Model implements Serializable, Cloneable {
        private String ModelName = null;
        private double ModelPrice = Double.NaN;

        public Model(String Name, double Price) {
            ModelName = Name;
            ModelPrice = Price;
        }

        public Model() { }

        public String getModelName() {
            return ModelName;
        }

        public double getModelPrice() {
            return ModelPrice;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    @Override
    public String[] getModelNamesArray() {
        String[] NamesArray = new String[ModelArray.length];
        for (int i = 0; i < ModelArray.length; i++) {
            if (ModelArray[i] != null) {
                NamesArray[i] = ModelArray[i].getModelName();
            }
        }
        return NamesArray;
    }

    @Override
    public double getPriceConcret(String modelName) throws NoSuchModelNameException {
        int id = -1;
        for (int i = 0; i < ModelArray.length; i++) {
            if (ModelArray[i].ModelName.equals(modelName)) {
                id = i;
                break;
            }
        }
        if (id == -1) throw new NoSuchModelNameException("Введенной модели не существует");
        else return ModelArray[id].getModelPrice();
    }

    @Override
    public void setConcretModelPrice(String modelName, double newPrice) throws NoSuchModelNameException {
        if (newPrice < 0) throw new ModelPriceOutOfBoundsException("Введено неверное значение цены");
        int id = -1;
        for (int i = 0; i < ModelArray.length; i++) {
            if (ModelArray[i].ModelName.equals(modelName)) {
                id = i;
                break;
            }
        }
        if (id == -1) throw new NoSuchModelNameException("Введенной модели не существует");
        else ModelArray[id].ModelPrice = newPrice;
    }

    @Override
    public double[] getModelPricesArray() {
        double[] PriceArray = new double[ModelArray.length];
        for (int i = 0; i < ModelArray.length; i++) {
            if (ModelArray[i] != null) {
                PriceArray[i] = ModelArray[i].getModelPrice();
            }
        }
        return PriceArray;
    }

    @Override
    public void setModelName(String oldNAme, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        int id = -1;
        for (int i = 0; i < ModelArray.length; i++) {
            if (ModelArray[i].ModelName.equals(oldNAme)) {
                id = i;
                break;
            }
        }
        for (int i = 0; i < ModelArray.length; i++) {
            if (ModelArray[i].ModelName.equals(newName))
                throw new DuplicateModelNameException("Такое имя уже существует");
        }
        if (id == -1) throw new NoSuchModelNameException("Введенной модели не существует");
        else ModelArray[id].ModelName = newName;
    }

    @Override
    public void addModel(String Name, double Price) throws DuplicateModelNameException {
        if (Price < 0) throw new ModelPriceOutOfBoundsException("Введено неверное значение цены");
        for (int i = 0; i < ModelArray.length; i++) {
            if (ModelArray[i].ModelName.equals(Name)) throw new DuplicateModelNameException("Такое имя уже существует");
        }
        ModelArray = Arrays.copyOf(ModelArray, ModelArray.length + 1);
        ModelArray[ModelArray.length - 1] = new Model(Name, Price);
    }

    @Override
    public void removeModel(String Name) throws NoSuchModelNameException {
        int id = -1;
        for (int i = 0; i < ModelArray.length; i++) {
            if (ModelArray[i].ModelName.equals(Name)) {
                id = i;
                break;
            }
        }
        if (id == -1) throw new NoSuchModelNameException("Введенной модели не существует");
        System.arraycopy(ModelArray, id + 1, ModelArray, id, ModelArray.length - id - 1);
        ModelArray = Arrays.copyOf(ModelArray, ModelArray.length - 1);
    }

    @Override
    public int getSize() {
        return ModelArray.length;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Марка Автомобиля ");
        stringBuffer.append(mark);
        stringBuffer.append(" ");
        stringBuffer.append("Количество моделей ");
        stringBuffer.append(getSize());
        stringBuffer.append(" ");
        for (int i = 0; i < getSize(); i++) {
            stringBuffer.append(" ");
            stringBuffer.append(ModelArray[i].ModelName);
            stringBuffer.append(": ");
            stringBuffer.append(ModelArray[i].ModelPrice);
            stringBuffer.append(" ");
        }
        return stringBuffer.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Auto) {
            Auto st = (Auto) obj;
            if (!st.getMark().equals(getMark())) {
                return false;
            } else if (st.getSize() != getSize()) {
                return false;
            }
            for (int i = 0; i < getSize(); i++) {
                if ((!st.ModelArray[i].ModelName.equals(ModelArray[i].ModelName))) {
                    return false;
                } else if (st.ModelArray[i].getModelPrice() != ModelArray[i].getModelPrice()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (mark != null) {
            if (ModelArray != null) {
                result = 31 * result + ModelArray.hashCode();
                return result;
            } else return 0;
        } else return 0;
    }

    @Override
    public Object clone() {
        Auto cloneAuto;
        try {
            cloneAuto = (Auto) super.clone();
            cloneAuto.ModelArray = ModelArray.clone();
            for (int i = 0; i < getSize(); i++) {
                cloneAuto.ModelArray[i] = (Model) ModelArray[i].clone();
            }
            return cloneAuto;
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
            return null;
        }
    }
}