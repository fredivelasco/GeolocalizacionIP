package com.developer.geoips.service;

import com.developer.geoips.dto.ResponseEstadisticaDTO;
import com.developer.geoips.dto.ResponseIPDTO;

import java.io.IOException;

public interface ConsultaIPService {



    ResponseIPDTO consultarInformacionIP(String direccionIp) throws IOException, Exception;

    ResponseEstadisticaDTO consultarEstadisticas();
}
