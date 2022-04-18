package com.myhotel.challenge.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.myhotel.challenge.utils.enums.Location;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonPropertyOrder({"description","field","value","location"})
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetails {

    @NotNull
    private String description;

    private String field;

    private Object value;

    private Location location;

    @Generated
    public static ErrorDetails.ErrorDetailsBuilder builder() {return new ErrorDetails.ErrorDetailsBuilder();}

    public static class ErrorDetailsBuilder {
        private String description;
        private String field;
        private Object value;
        private Location location;

        ErrorDetailsBuilder(){}

        public ErrorDetails.ErrorDetailsBuilder description(String description){
            this.description = description;
            return this;
        }

        public ErrorDetails.ErrorDetailsBuilder field(String field){
            this.field = field;
            return this;
        }

        public ErrorDetails.ErrorDetailsBuilder value(Object value){
            this.value = value;
            return this;
        }

        public ErrorDetails.ErrorDetailsBuilder location(Location location){
            this.location = location;
            return this;
        }

        public ErrorDetails build() {
            return new ErrorDetails(this.description,this.field,this.value,this.location);
        }
    }
}
