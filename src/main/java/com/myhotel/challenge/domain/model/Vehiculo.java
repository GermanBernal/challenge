package com.myhotel.challenge.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "myhotel-test",name = "vehiculo")
@Data
public class Vehiculo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "VEHICULO_ID")
    private Integer vechiculoId;

    @Column(name = "MARCA")
    private String marca;

    @Column(name = "MODELO")
    private String modelo;

    @Column(name = "PATENTE")
    private String patente;

    @Column(name = "ANIO")
    private Integer anio;

    @Column(name = "KILOMETRAJE")
    private Double kilometraje;

    @Column(name = "CILINDRADA")
    private Double cilindrada;

    @Column(name = "BORRADO")
    private Boolean borrado = false;

    @OneToMany(mappedBy = "vehiculo")
    private Set<Mantenimiento> mantenimeintos;

}
