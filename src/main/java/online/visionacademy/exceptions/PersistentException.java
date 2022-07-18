package online.visionacademy.exceptions;

public class PersistentException extends Exception{


    public PersistentException(String message){
        super(message);
    }

    public PersistentException(String message, Throwable cause){
        super(message,cause);
    }

}
