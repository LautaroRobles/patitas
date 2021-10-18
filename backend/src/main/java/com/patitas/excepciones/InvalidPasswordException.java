package com.patitas.excepciones;

public class InvalidPasswordException extends Exception{
    // Constructor that accepts a message
    public InvalidPasswordException(String message)
    {
        super(message);
    }
}
