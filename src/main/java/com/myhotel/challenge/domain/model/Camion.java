package com.myhotel.challenge.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Data
@Table(schema = "myhotel-test",name = "camion")
@PrimaryKeyJoinColumn(name = "CAMION_ID")
public class Camion extends Vehiculo{

    @Column(name = "TIPO")
    private String tipo;

    @Column(name = "CAPACIDAD")
    private Double capacidad;

    @Column(name = "EJES")
    private Integer ejes;

}