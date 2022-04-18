package com.myhotel.challenge.domain.usecase.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
public class CamionDto extends VehiculoDto implements Serializable {

    @NotNull
    @Schema(description = "Tipo de camion", example = "tolva", required = true)
    private String tipo;

    @NotNull
    @Schema(description = "Capacidad del camion", example = "5000", required = true)
    private Double capacidad;

    @NotNull
    @Schema(description = "Cantidad de ejes del camion", example = "4", required = true)
    private Integer ejes;
}
