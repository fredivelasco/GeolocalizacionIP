package com.developer.geoips.external.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private float geoname_id;
    private String capital;
    ArrayList<LanguageDTO> languages = new ArrayList<LanguageDTO>();
    private String country_flag;
    private String country_flag_emoji;
    private String country_flag_emoji_unicode;
    private String calling_code;
    private boolean is_eu;
}
