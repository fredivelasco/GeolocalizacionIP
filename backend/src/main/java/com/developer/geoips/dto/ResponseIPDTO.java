package com.developer.geoips.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseIPDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String direccionIp;
    private String fechaActual;
    private String nombrePais;
    private String codigoISOPais;
    private String idiomaPais;
    private String monedaPais;
    private String horaPais;
    private String distanciaPais;





}
