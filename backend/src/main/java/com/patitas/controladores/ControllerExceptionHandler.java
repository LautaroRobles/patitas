package com.patitas.controladores;

import com.patitas.dto.ErrorMessageDTO;
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
    ErrorMessageDTO usernameNotFoundHandler(UsernameNotFoundException exception) {
        ErrorMessageDTO error = new ErrorMessageDTO();
        error.setMessage(exception.getMessage());
        return error;
    }

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorMessageDTO notFoundHandler(NotFoundException exception) {
        ErrorMessageDTO error = new ErrorMessageDTO();
        error.setMessage(exception.getMessage());
        return error;
    }

    @ResponseBody
    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    ErrorMessageDTO invalidPasswordHandler(InvalidPasswordException exception) {
        ErrorMessageDTO error = new ErrorMessageDTO();
        error.setMessage(exception.getMessage());
        return error;
    }

    @ResponseBody
    @ExceptionHandler(UsernameAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    ErrorMessageDTO usernameAlreadyTakenHandler(UsernameAlreadyTakenException exception) {
        ErrorMessageDTO error = new ErrorMessageDTO();
        error.setMessage(exception.getMessage());
        return error;
    }

    @ResponseBody
    @ExceptionHandler(PublicationTypeException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    ErrorMessageDTO publicationTypeHandler(PublicationTypeException exception) {
        ErrorMessageDTO error = new ErrorMessageDTO();
        error.setMessage(exception.getMessage());
        return error;
    }
}