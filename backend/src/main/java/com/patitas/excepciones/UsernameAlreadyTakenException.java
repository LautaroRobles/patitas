package com.patitas.excepciones;

public class UsernameAlreadyTakenException extends Exception{
    // Constructor that accepts a message
    public UsernameAlreadyTakenException(String message)
    {
        super(message);
    }
}
