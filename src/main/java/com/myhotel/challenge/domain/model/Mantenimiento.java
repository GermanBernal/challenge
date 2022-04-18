package com.myhotel.challenge.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(schema = "myhotel-test",name = "mantenimiento")
@Data
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MANTENIMIENTO_ID")
    private Integer id;

    @Column(name = "FECHA_INGRESO")
    private Date fechaIngreso;

    @Column(name = "FECHA_FINALIZACION")
    private Date fechaFinalizacion;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "COMENTARIO")
    private String comentario;

    @Column(name = "PRECIO")
    private BigDecimal precio;

    @Column(name = "BORRADO")
    private boolean borrado = false;

    @ManyToOne
    @JoinColumn(name = "VEHICULO_ID", referencedColumnName = "VEHICULO_ID")
    private Vehiculo vehiculo;

}
