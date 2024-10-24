package com.developer.geoips.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;


@Entity
@Table(name = "estadistica", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estadistica implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="sequenciaEstadistica")
    @SequenceGenerator(name="sequenciaEstadistica",sequenceName="estadistica_estadistica_id_seq", allocationSize=1)
    @Column(name = "estadistica_id", unique = true)
    @Basic(optional = false)
    private Long estadisticaId;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "pais")
    private String pais;
    @Column(name = "distancia")
    private Double distancia;
    @Column(name = "invocaciones")
    private Integer invocaciones;

}