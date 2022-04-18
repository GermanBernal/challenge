package com.myhotel.challenge.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends MyHotelHttpException {

    private static final String MESSAGE = "El recurso que esta intentando modificar no existe o se encuentra eliminado";

    public ResourceNotFoundException() {
        super(MESSAGE, HttpStatus.NOT_FOUND);
    }
}
