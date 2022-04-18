package com.myhotel.challenge.infraestructure.interceptors;

import com.myhotel.challenge.exceptions.MyHotelHttpException;
import com.myhotel.challenge.utils.ErrorDetails;
import com.myhotel.challenge.utils.ResponseEntityBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({MyHotelHttpException.class})
    @Nullable
    public final ResponseEntity<?> handleException(MyHotelHttpException ex, final WebRequest request) throws Exception {
        log.error("Response Entity Exception due " + ex, ex);
        return this.handleExceptionInternal(ex);
    }

    protected ResponseEntity<?> handleExceptionInternal(MyHotelHttpException ex) {
        List<ErrorDetails> errors = List.of(ErrorDetails.builder().description(ex.getMessage()).build());
        return (new ResponseEntityBuilder<>(ex.getStatus())).errors(errors).build();
    }

}
