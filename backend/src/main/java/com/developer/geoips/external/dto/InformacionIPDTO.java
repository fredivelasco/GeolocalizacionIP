package com.developer.geoips.external.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformacionIPDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ip;
    private String type;
    private String continent_code;
    private String continent_name;
    private String country_code;
    private String country_name;
    private String region_code;
    private String region_name;
    private String city;
    private String zip;
    private double latitude;
    private double longitude;
    private String msa;
    private String dma;
    private double radius;
    private String ip_routing_type;
    private String connection_type;
    private LocationDTO location;
}
