package com.myhotel.challenge.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "myhotel-test",name = "automovil")
@PrimaryKeyJoinColumn(name = "AUTOMOVIL_ID")
public class Auto extends Vehiculo {

    @Column(name = "TIPO")
    private String tipo;

    @Column(name = "NUMERO_PUERTAS")
    private Integer numeroPuertas;

    @Column(name = "CAPACIDAD_PASAJEROS")
    private Integer capacidadPasajero;

    @Column(name = "CAPACIDAD_MALETEROS")
    private Double capacidadMaletero;

}
