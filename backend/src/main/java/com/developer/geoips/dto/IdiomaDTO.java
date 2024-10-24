package com.developer.geoips.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdiomaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String code;
}
