package com.developer.geoips.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadisticaDTO implements Serializable {

    private String codigo;
    private String pais;
    private Double distancia;
    private Integer invocaciones;

}