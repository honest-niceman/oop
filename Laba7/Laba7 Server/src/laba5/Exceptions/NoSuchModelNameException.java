package laba5.Exceptions;

public class NoSuchModelNameException extends Exception {
    public NoSuchModelNameException(){ }
    public NoSuchModelNameException(String Message){
        super(Message);
    }
}
