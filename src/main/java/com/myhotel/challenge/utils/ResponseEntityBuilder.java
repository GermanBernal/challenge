package com.myhotel.challenge.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonPropertyOrder({"_data","_errors","_status"})
public class ResponseEntityBuilder<T> {

    @JsonProperty("_data")
    private T data;

    @JsonProperty("_errors")
    private List<ErrorDetails> errors;

    @JsonProperty("_status")
    private HttpStatus status;

    public ResponseEntityBuilder(@NotNull HttpStatus status){
        this.errors = new ArrayList<>();
        this.status = status;
    }

    public static <T> ResponseEntityBuilder<T> builder() {return new ResponseEntityBuilder(HttpStatus.I_AM_A_TEAPOT);}

    public ResponseEntityBuilder<T> status(HttpStatus status){
        this.status = status;
        return this;
    }

    public ResponseEntityBuilder<T> data(T data){
        this.data = data;
        return this;
    }

    public ResponseEntityBuilder<T> addError(ErrorDetails err){
        this.errors.add(err);
        return this;
    }

    public List<ErrorDetails> getErrors() {return new ArrayList(this.errors);}

    public ResponseEntityBuilder<T> errors(List<ErrorDetails> errors){
        this.errors = errors;
        return this;
    }

    public T getData(){
        return this.data;
    }

    public HttpStatus getStatus(){return this.status;}



    public ResponseEntity<T> build(){
        return Objects.isNull(this.getData()) && this.getErrors().isEmpty()
                ? new ResponseEntity(this.getStatus())
                : new ResponseEntity(this,this.getStatus());
    }
}
