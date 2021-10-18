package com.patitas.controladores;

import com.patitas.excepciones.InvalidPasswordException;
import com.patitas.excepciones.PublicationTypeException;
import com.patitas.excepciones.UsernameAlreadyTakenException;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseBody
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String usernameNotFoundHandler(UsernameNotFoundException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String notFoundHandler(NotFoundException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String invalidPasswordHandler(InvalidPasswordException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UsernameAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String usernameAlreadyTakenHandler(UsernameAlreadyTakenException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PublicationTypeException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String publicationTypeHandler(PublicationTypeException exception) {
        return exception.getMessage();
    }
}