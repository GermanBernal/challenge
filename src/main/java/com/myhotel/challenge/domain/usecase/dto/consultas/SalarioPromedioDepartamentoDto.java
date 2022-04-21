package com.myhotel.challenge.domain.usecase.dto.consultas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalarioPromedioDepartamentoDto {

    @NotNull
    @Schema(description = "ID del departamento", example = "1")
    private Integer idDepartamento;

    @Schema(description = "Nombre del departamento", example = "Shipping")
    private String departamento;

    @Schema(description = "Promedio del salario del departamento", example = "123.30")
    private Double salarioPromedio;

}
