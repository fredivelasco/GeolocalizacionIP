package com.developer.geoips.service;


import com.developer.geoips.model.Estadistica;

import java.util.Optional;


public interface EstadisticaService extends GenericService<Estadistica, Long> {
    public Optional<Estadistica> findByCodigo(String codigo);

}
