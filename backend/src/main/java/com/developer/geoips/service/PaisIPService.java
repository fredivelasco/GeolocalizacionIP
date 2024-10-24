package com.developer.geoips.service;


import com.developer.geoips.model.Estadistica;
import com.developer.geoips.model.PaisIP;

import java.util.Optional;


public interface PaisIPService extends GenericService<PaisIP, Long> {

    public Optional<PaisIP> findByDireccionIp(String direccionIp);

}
