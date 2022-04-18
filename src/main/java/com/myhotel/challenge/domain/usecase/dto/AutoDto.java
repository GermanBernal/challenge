package com.myhotel.challenge.domain.usecase.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AutoDto extends VehiculoDto implements Serializable {

    @NotNull
    @Schema(description = "Tipo del auto", example = "suv", required = true)
    private String tipo;

    @NotNull
    @Schema(description = "Cantidad de puertas del auto", example = "4", required = true)
    private Integer numeroPuertas;

    @NotNull
    @Schema(description = "Capacidad maxima de pasajeros", example = "5", required = true)
    private Integer capacidadPasajero;

    @NotNull
    @Schema(description = "Capacidad del maletero", example = "1500", required = true)
    private Double capacidadMaletero;
}
