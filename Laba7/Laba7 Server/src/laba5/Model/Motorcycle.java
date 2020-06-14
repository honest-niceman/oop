package laba5.Model;
import laba5.Exceptions.*;
import laba5.Interface.*;

import java.io.Serializable;

public class Motorcycle implements Vehicle, Cloneable {
    public Motorcycle() { }
    public Motorcycle(String Mark, int n) throws DuplicateModelNameException {
        mark = Mark;
        InitialSize=0;
        for (int i = 0; i < n; i++) {
            this.addModel("Model"+(i+1),i*i*i);
        }
    }
    private class Model implements Serializable, Cloneable {
        String name = null;
        double price = Double.NaN;
        Model prev = null;
        Model next = null;

        public Model(String Name, double Price) {
            name = Name;
            price = Price;
        }

        public Model() { }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
    private Model head = new Model();
    {
        head.prev = head;
        head.next = head;
    }
    private String mark;
    private int InitialSize;
    @Override
    public String getMark() {
        return mark;
    }
    @Override
    public void setMark(String mark) {
        this.mark = mark;
    }
    @Override
    public double getPriceConcret(String Name) throws NoSuchModelNameException // Редактирование
    {
        Model Head = head.next;
        if (Head != null) {
            while ((Head.next != head) && !(Head.name.equals(Name))) { // equals
                Head = Head.next;
            }
            if (!(Head.name.equals(Name))) throw new NoSuchModelNameException("Такой модели не существует");
            return Head.price;
        } else return 0;
    }
    @Override
    public void setConcretModelPrice(String Name, double newPrice) throws NoSuchModelNameException {
        if(newPrice < 0) throw new ModelPriceOutOfBoundsException("Введено неверное значение цены");
        Model Head = head.next;
        int id = -1;
        for (int i = 0; i < InitialSize; i++) {
            if (Head.name.equals(Name)) {
                id = i;
                break;
            }
            Head = Head.next;
        }
        if (id == -1) throw new NoSuchModelNameException("Введенной модели не существует");
        else Head.price = newPrice;
    }
    @Override
    public String[] getModelNamesArray() {
        String[] NamesArray = new String[InitialSize];
        Model Head = head.next;
        for (int i = 0; i < NamesArray.length; i++) {
            NamesArray[i] = Head.name;
            Head = Head.next;
        }
        return NamesArray;
    }
    @Override
    public double[] getModelPricesArray() {
        double[] PricesArray = new double[InitialSize];
        Model Head = head.next;
        for (int i = 0; i < PricesArray.length; i++) {
            PricesArray[i] = Head.price;
            Head = Head.next;
        }
        return PricesArray;
    }
    @Override
    public int getSize(){ return InitialSize; }
    @Override
    public void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        Model p = head;
        do{
            p=p.next;
        }while((p.next!=head) && (!p.name.equals(oldName)));// equals
        if(!(p.name.equals(oldName))) throw new NoSuchModelNameException("Такой модели не существует");
        testDuplicate(newName);
        p.name = newName;
    }
    @Override
    public void removeModel(String Name) throws NoSuchModelNameException {
        Model Head = head;
        do {
            Head = Head.next;
        } while (Head.next != head && (!(Head.name.equals(Name))));
        if (!Head.name.equals(Name)) throw new NoSuchModelNameException("Такой модели не существует");
        Head.prev.next = Head.next;
        Head.next.prev = Head.prev;
        InitialSize--;
    }
    public void testDuplicate(String newName) throws DuplicateModelNameException {
        Model Test = head.next;
        int id = -1;
        while (Test != head && !Test.name.equals(newName)) {
            Test = Test.next;
        }
        if (Test != head){
            id = getIndex(Test);
        }
        if (id != -1) throw new DuplicateModelNameException("Модель с таким именем уже существует");
    }
    private int getIndex(Model test) {
        Model p = head.next;
        if (p == head)
            return  -1;
        else {
            int i=0;
            while (p!=head && p!=test) {
                i++;
                p=p.next;
            }
            if (p == head) return -1;
            else return  i;
        }
    }
    @Override
    public void addModel(String Name, double Price) throws  DuplicateModelNameException {
        if (Price < 0) throw new ModelPriceOutOfBoundsException("Введено неверное значение цены");
        Model Head = new Model(Name, Price);
        if (head.next == head) {
            head.next = Head;
            head.prev = Head;
            Head.prev=head;
            Head.next=head;
        } else {
            testDuplicate(Name);
            Head.prev = head.prev;
            Head.next = head;
            head.prev.next = Head;
            head.prev = Head;
        }
        InitialSize++;
    }
    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Марка Мотоцикла ");
        stringBuffer.append(mark);
        stringBuffer.append(" ");
        stringBuffer.append("Количество моделей ");
        stringBuffer.append(getSize());
        stringBuffer.append(" ");
        Model Head = head.next;
        for (int i = 0; i < getSize(); i++) {
            stringBuffer.append(" ");
            stringBuffer.append(Head.name);
            stringBuffer.append(": ");
            stringBuffer.append(Head.price);
            stringBuffer.append(" ");
            Head = Head.next;
        }
        return stringBuffer.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj==null) {
            return false;
        }
        if (obj instanceof Motorcycle) {
            Motorcycle st = (Motorcycle) obj;
            if (!st.getMark().equals(getMark())) {
                return false;
            } else if (st.getSize() != getSize()) {
                return false;
            }
            for (int i = 0; i < getSize(); i++) {
                Model stHead = st.head.next;
                Model Head = head.next;
                if ((!stHead.name.equals(Head.name))) {
                    return false;
                } else if (stHead.price != Head.price) {
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
        Model Head = head.next;
        if (mark != null) {
            if (Head == head) {
                return 0;
            } else {
                while (Head != head) {
                    result = 31 * result + Head.name.hashCode();
                    Head = Head.next;
                }
                return result;
            }
        } else return 0;
    }
    @Override
    public Object clone() {
        try {
            Motorcycle cloneMoto = (Motorcycle) super.clone();
            cloneMoto.head = new Model();
            cloneMoto.head.prev = cloneMoto.head;
            cloneMoto.head.next = cloneMoto.head;
            Model Head = this.head.next;
            while (Head != head) {
                Model clone = (Model) Head.clone();
                cloneMoto.head.prev.next = clone;
                clone.next = cloneMoto.head;
                clone.prev = cloneMoto.head.prev;
                cloneMoto.head.prev = clone;
                Head = Head.next;
            }
            return cloneMoto;
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
            return null;
        }
    }
}
