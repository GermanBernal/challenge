package com.myhotel.challenge.domain.usecase.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
public class VehiculoDto implements Serializable {

    @NotNull
    @NotBlank
    @Schema(description = "Marca del vehiculo", example = "Toyota", required = true)
    private String marca;

    @NotNull
    @NotBlank
    @Schema(description = "Modelo del vehiculo", example = "Etios", required = true)
    private String modelo;

    @NotNull
    @NotBlank
    @Schema(description = "Patente del vehiculo", example = "PJY 123", required = true)
    private String patente;

    @NotNull
    @Schema(description = "Año del modelo", example = "2020", required = true)
    private Integer anio;

    @NotNull
    @Schema(description = "Kilometraje marcado por el taquimetro del vehiculo", example = "24020.30", required = true)
    private Double kilometraje;

    @NotNull
    @Schema(description = "Cilindrada en cm3 del vehiculo", example = "100", required = true)
    private Double cilindrada;
}
