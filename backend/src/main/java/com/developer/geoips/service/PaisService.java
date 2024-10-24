package com.developer.geoips.service;

import com.developer.geoips.model.Pais;

import java.util.Optional;


public interface PaisService extends GenericService<Pais, Long> {
    public Optional<Pais> findByCodeISO(String countryCode);
}
