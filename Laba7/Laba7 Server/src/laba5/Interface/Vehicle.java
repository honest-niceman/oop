package laba5.Interface;
import laba5.Exceptions.*;
import java.io.Serializable;

public interface Vehicle extends Serializable {
    String getMark();
    void setMark(String Mark);
    void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;
    String[] getModelNamesArray();
    double[] getModelPricesArray();
    int getSize();
    double getPriceConcret(String Name) throws NoSuchModelNameException;
    void setConcretModelPrice(String Name, double Price) throws NoSuchModelNameException;
    void addModel(String Name, double Price) throws DuplicateModelNameException;
    void removeModel(String Name) throws NoSuchModelNameException;
}
