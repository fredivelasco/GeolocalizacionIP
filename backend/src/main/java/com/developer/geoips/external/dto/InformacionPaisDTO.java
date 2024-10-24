package com.developer.geoips.external.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformacionPaisDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    ArrayList<Object> topLevelDomain = new ArrayList<Object>();
    private String alpha2Code;
    private String alpha3Code;
    ArrayList<Object> callingCodes = new ArrayList<Object>();
    private String capital;
    ArrayList<Object> altSpellings = new ArrayList<Object>();
    private String region;
}
