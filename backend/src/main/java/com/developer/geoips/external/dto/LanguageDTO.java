package com.developer.geoips.external.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LanguageDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String name;
    private String nativeLanguage;
}
