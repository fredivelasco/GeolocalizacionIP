package com.developer.geoips.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEstadisticaDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    ArrayList<EstadisticaDTO> estadisticas = new ArrayList<EstadisticaDTO>();
    private Double promedio;




}
