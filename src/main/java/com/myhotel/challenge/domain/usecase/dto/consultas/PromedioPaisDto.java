package com.myhotel.challenge.domain.usecase.dto.consultas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PromedioPaisDto {

    @Schema(description = "nombre del pais")
    private String countryName;

    @Schema(description = "cantidad de empleados")
    private Integer employeesNumber;

    @Schema(description = "salario promedio")
    private Double averageSalary;

    @Schema(description = "salario maximo")
    private Double maxSalary;

    @Schema(description = "salario minimo")
    private Double minSalary;

    @Schema(description = "promedio antiguedad")
    private Double antiquityAverage;


}
