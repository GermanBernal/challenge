package com.myhotel.challenge.domain.usecase.dto.consultas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SegmentoCantidadDepartamentosDto {

    @NotNull
    @Schema(description = "Nombre del segmento", example = "SEGMENTO A")
    private String segmento;

    @Schema(description = "Cantidad de departamentos del segmento", example = "123")
    private Integer cantidadDepartamentos;

}
