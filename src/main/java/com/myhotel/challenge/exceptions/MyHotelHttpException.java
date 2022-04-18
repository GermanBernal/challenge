package com.myhotel.challenge.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MyHotelHttpException extends Throwable {
    private String friendlyMessage;
    private HttpStatus status;

    public MyHotelHttpException(String friendlyMessage) {super(friendlyMessage);}
    public MyHotelHttpException(String friendlyMessage, Throwable t) {super(friendlyMessage,t);}
    public MyHotelHttpException(String friendlyMessage, HttpStatus status){
        super(friendlyMessage);
        this.status = status;
        this.friendlyMessage = friendlyMessage;
    }
}
