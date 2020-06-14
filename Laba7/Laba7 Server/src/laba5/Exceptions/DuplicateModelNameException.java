package laba5.Exceptions;

public class DuplicateModelNameException extends Exception {
    public DuplicateModelNameException(){ }
    public DuplicateModelNameException(String Message){
        super(Message);
    }
}
