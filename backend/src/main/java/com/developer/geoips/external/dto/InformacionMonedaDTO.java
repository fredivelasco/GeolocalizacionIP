package com.developer.geoips.external.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformacionMonedaDTO implements Serializable {
    private boolean success;
    private float timestamp;
    private String base;
    private String date;
    ArrayList<Object> rates = new ArrayList<Object>();
}
