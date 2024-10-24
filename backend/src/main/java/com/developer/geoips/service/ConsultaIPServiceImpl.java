package com.developer.geoips.service;

import com.developer.geoips.dto.DistanciaDTO;
import com.developer.geoips.dto.EstadisticaDTO;
import com.developer.geoips.dto.ResponseEstadisticaDTO;
import com.developer.geoips.dto.ResponseIPDTO;
import com.developer.geoips.external.client.InformacionIPApiClient;
import com.developer.geoips.external.dto.InformacionIPDTO;
import com.developer.geoips.external.dto.LanguageDTO;
import com.developer.geoips.model.Estadistica;
import com.developer.geoips.model.Pais;
import com.developer.geoips.model.PaisIP;
import com.developer.geoips.model.PaisIdioma;
import com.developer.geoips.utils.DateUtils;
import com.developer.geoips.utils.GeoUtils;
import com.developer.geoips.utils.Utilities;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ConsultaIPServiceImpl implements ConsultaIPService {

    @Autowired
    private InformacionIPApiClient informacionIPApiClient;

    @Autowired
    private EstadisticaService estadisticaService;

    @Autowired
    private PaisIPService paisIPService;

    @Autowired
    private PaisService paisService;



    @Override
    public ResponseIPDTO consultarInformacionIP(String direccionIp) throws IOException, Exception {


        InformacionIPDTO informacionIPDTO = new InformacionIPDTO();
        DistanciaDTO distanciaDTO = new DistanciaDTO();
        ResponseIPDTO responseIPDTO = new ResponseIPDTO();

//  Consultar en la base de datos
        Optional<PaisIP> paisIPOptional = paisIPService.findByDireccionIp(direccionIp);
        if (paisIPOptional.isPresent()) {

            PaisIP paisIP = paisIPOptional.get();
            //System.out.println("Informacion desde la Base de datos");

            responseIPDTO = cargarInformacionResponse(paisIP);

            distanciaDTO = GeoUtils.calcularDistanciaBuenosAires(paisIP.getPais().getLatitud(), paisIP.getPais().getLongitud());

            actualizarEstadisticas(paisIP.getPais().getNombre(), distanciaDTO.getDistancia());

        } else {

//  Obtener datos desde la API
            //System.out.println("Informacion desde la API");

            informacionIPDTO = informacionIPApiClient.obtenerInformacionIP(direccionIp);

            Pais newPais = new Pais();
            PaisIP newPaisIP = new PaisIP();
            // Validar si ya exite el pais
            Optional<Pais> paisOptional = paisService.findByCodeISO(informacionIPDTO.getCountry_code());
            if (paisOptional.isPresent()) {
                newPais = paisOptional.get();
            } else {

                newPais.setNombre(informacionIPDTO.getCountry_name());
                newPais.setCodigoIso(informacionIPDTO.getCountry_code());
                newPais.setLatitud(informacionIPDTO.getLatitude());
                newPais.setLongitud(informacionIPDTO.getLongitude());


                ArrayList<LanguageDTO> languages = informacionIPDTO.getLocation().getLanguages();


                for (LanguageDTO language : languages) {
                    PaisIdioma idioma = new PaisIdioma();
                    idioma.setCodigo(language.getCode());
                    idioma.setNombre(language.getName());
                    newPais.addIdioma(idioma);

                }


                distanciaDTO = GeoUtils.calcularDistanciaBuenosAires(informacionIPDTO.getLatitude(), informacionIPDTO.getLongitude());

                newPais.setDistancia(distanciaDTO.getDistancia());

            }

            newPaisIP.setDireccionIP(informacionIPDTO.getIp());
            newPaisIP.setPais(newPais);

            actualizarEstadisticas(newPais.getNombre(), distanciaDTO.getDistancia());
            paisIPService.save(newPaisIP);

            responseIPDTO = cargarInformacionResponse(newPaisIP);

        }


        return responseIPDTO;


    }

    @Override
    public ResponseEstadisticaDTO consultarEstadisticas() {

        ArrayList<Estadistica> estadisticas = (ArrayList<Estadistica>) estadisticaService.findAll();

        ResponseEstadisticaDTO responseEstadisticaDTO = new ResponseEstadisticaDTO();

        ArrayList<EstadisticaDTO> estadisticasDTO = new ArrayList<>();

        double valorTotal = 0.0;
        Integer numeroInvocaciones = 0;
        for (Estadistica estadistica : estadisticas) {

            EstadisticaDTO estadisticaDTO = new EstadisticaDTO();
            estadisticaDTO.setCodigo(estadistica.getCodigo());
            estadisticaDTO.setPais(estadistica.getPais());
            estadisticaDTO.setDistancia(estadistica.getDistancia());
            estadisticaDTO.setInvocaciones(estadistica.getInvocaciones());

            valorTotal += estadistica.getDistancia() * estadistica.getInvocaciones();
            numeroInvocaciones += estadistica.getInvocaciones();

            estadisticasDTO.add(estadisticaDTO);

        }

        responseEstadisticaDTO.setEstadisticas(estadisticasDTO);
        responseEstadisticaDTO.setPromedio(valorTotal / numeroInvocaciones);

        return responseEstadisticaDTO;


    }

    private ResponseIPDTO cargarInformacionResponse(PaisIP paisIP) {

        ResponseIPDTO responseIPDTO = new ResponseIPDTO();

        responseIPDTO.setDireccionIp(paisIP.getDireccionIP());
        responseIPDTO.setFechaActual(DateUtils.fechaActualFormateada());
        responseIPDTO.setNombrePais(paisIP.getPais().getNombre());
        responseIPDTO.setCodigoISOPais(paisIP.getPais().getCodigoIso());


        StringBuilder idiomas = new StringBuilder();
        for (PaisIdioma idioma : paisIP.getPais().getIdiomas()) {
            idiomas.append(idioma.getNombre()).append(" (").append(idioma.getCodigo()).append(") ");

        }

        responseIPDTO.setIdiomaPais(String.valueOf(idiomas));
        responseIPDTO.setMonedaPais("--");

        String coordenadas = GeoUtils.coordenadasBuenosAires(paisIP.getPais().getLatitud(), paisIP.getPais().getLongitud());
        String distanciaPais = Utilities.doubleToString(paisIP.getPais().getDistancia()) + " kms " + coordenadas;

        responseIPDTO.setHoraPais( DateUtils.horaActualUTC("UTC+01:00") + " (UTC+01:00)" + "  o  " + DateUtils.horaActualUTC("UTC-05:00") + " (UTC-05:00)"  );
        responseIPDTO.setDistanciaPais(distanciaPais);

        return responseIPDTO;


    }

    private void actualizarEstadisticas(String nombrePais, double distancia) throws Exception {
        final String MINIMA = "MINIMA";
        final String MAXIMA = "MAXIMA";

        boolean actualizarMinima = false;
        boolean actualizarMaxima = false;

        Estadistica minima = new Estadistica();
        Estadistica maxima = new Estadistica();


        Optional<Estadistica> minimaOPt = estadisticaService.findByCodigo(MINIMA);
        Optional<Estadistica> maximaOPt = estadisticaService.findByCodigo(MAXIMA);

        if (minimaOPt.isPresent() && maximaOPt.isPresent()) {
            double distanciaMinima = minimaOPt.get().getDistancia();
            double distanciaMaxima = maximaOPt.get().getDistancia();
            double difereniaMinima = Math.abs(distancia-distanciaMinima);
            double diferenciaMaxima = Math.abs(distancia-distanciaMaxima);
            if(difereniaMinima < diferenciaMaxima){
                actualizarMinima = true;
            }else{
                actualizarMaxima = true;
            }

        }


        if (minimaOPt.isPresent()) {
            minima = minimaOPt.get();

            if (distancia < minima.getDistancia()) {
                minima.setCodigo(MINIMA);
                minima.setPais(nombrePais);
                minima.setDistancia(distancia);
                minima.setInvocaciones(minima.getInvocaciones() + 1);
            } else {
                if(actualizarMinima) {
                    minima.setInvocaciones(minima.getInvocaciones() + 1);
                }
            }
            estadisticaService.update(minima);

        } else {

            minima.setCodigo(MINIMA);
            minima.setPais(nombrePais);
            minima.setDistancia(distancia);
            minima.setInvocaciones(1);

            estadisticaService.save(minima);

        }

        if (maximaOPt.isPresent()) {
            maxima = maximaOPt.get();

            if (distancia > maxima.getDistancia()) {
                maxima.setCodigo(MAXIMA);
                maxima.setPais(nombrePais);
                maxima.setDistancia(distancia);
                maxima.setInvocaciones(maxima.getInvocaciones() + 1);
            } else {
                if(actualizarMaxima) {
                    maxima.setInvocaciones(maxima.getInvocaciones() + 1);
                }
            }
            estadisticaService.update(maxima);
        } else {
            maxima = new Estadistica();
            maxima.setCodigo(MAXIMA);
            maxima.setPais(nombrePais);
            maxima.setDistancia(distancia);
            maxima.setInvocaciones(1);
            estadisticaService.save(maxima);
        }


    }


}
