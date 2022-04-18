package com.myhotel.challenge.domain.usecase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MantenimientoFullResponseDto {
    @NotNull
    @Schema(description = "Fecha de ingreso a mantenimiento del vehiculo", example = "2020-01-01", required = true)
    private Date fechaIngreso;

    @Schema(description = "Fecha de finalizacion del mantenimiento del vehiculo", example = "2020-01-11")
    private Date fechaFinalizacion;

    @NotNull
    @NotBlank
    @Schema(description = "Descripcion del mantenimiento a realizar", example = "Cambio de aceite", required = true)
    private String descripcion;


    @Schema(description = "Comentario adicional del mantenimiento del vehiculo", example = "Limpiar filtros de aire")
    private String comentario;

    @NotNull
    @Schema(description = "Costo del mantenimiento realizado", example = "2123.50", required = true)
    private Double precio;

    @NotNull
    @Schema(description = "Id del vehiculo el cual sera registrado para mantenimiento", example = "1", required = true)
    private Integer vehiculoId;

    @NotBlank
    @Schema(description = "Marca del vehiculo", example = "Toyota", required = true)
    @JsonProperty("marca")
    private String vehiculoMarca;

    @NotNull
    @NotBlank
    @Schema(description = "Modelo del vehiculo", example = "Etios", required = true)
    @JsonProperty("modelo")
    private String vehiculoModelo;

    @NotNull
    @NotBlank
    @Schema(description = "Patente del vehiculo", example = "PJY 123", required = true)
    @JsonProperty("patente")
    private String vehiculoPatente;

    @NotNull
    @Schema(description = "Año del modelo", example = "2020", required = true)
    @JsonProperty("anio")
    private Integer vehiculoAnio;

    @NotNull
    @Schema(description = "Kilometraje marcado por el taquimetro del vehiculo", example = "24020.30", required = true)
    @JsonProperty("kilometraje")
    private Double vehiculoKilometraje;

    @NotNull
    @Schema(description = "Cilindrada en cm3 del vehiculo", example = "100", required = true)
    @JsonProperty("cilindrada")
    private Double vehiculoCilindrada;
}
