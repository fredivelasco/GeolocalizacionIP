package com.developer.geoips.controller;


import com.developer.geoips.dto.ResponseEstadisticaDTO;
import com.developer.geoips.dto.ResponseIPDTO;
import com.developer.geoips.service.ConsultaIPService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {
    @Autowired
    private ConsultaIPService consultaIPService;

    @GetMapping(value = "/{ip}")
    public ResponseEntity<?> consultarIp(
            @PathVariable("ip")
            String direccionIp) throws Exception {

        ResponseIPDTO informacionIpDto = new ResponseIPDTO();

        try {

            informacionIpDto = consultaIPService.consultarInformacionIP(direccionIp);

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return ResponseEntity.ok()
                .body(informacionIpDto);

    }


    @GetMapping(value = "/estadisticas")
    public ResponseEntity<?> consultarEstadisticas(
           ) throws Exception {

        ResponseEstadisticaDTO estadisticasDto = new ResponseEstadisticaDTO();

        try {

            estadisticasDto = consultaIPService.consultarEstadisticas();

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return ResponseEntity.ok()
                .body(estadisticasDto);

    }


}
