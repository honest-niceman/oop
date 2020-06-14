package laba5.Exceptions;

public class ModelPriceOutOfBoundsException extends RuntimeException {
    public ModelPriceOutOfBoundsException(){}
    public ModelPriceOutOfBoundsException(String Message){
        super(Message);
    }
}
