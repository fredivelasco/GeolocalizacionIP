package com.developer.geoips.external.client;

import com.developer.geoips.exception.ApiInformacionIPException;
import com.developer.geoips.external.dto.InformacionMonedaDTO;
import com.developer.geoips.external.dto.InformacionPaisDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class InformacionMonedaApiClient {

    @Value("${api.informacionMoneda.url}")
    private String urlBase;

    @Value("${api.informacionMoneda.access_key}")
    private String access_key;

public InformacionMonedaDTO obtenerInformacionMoneda(String codigoMoneda) throws IOException{
    String apiUrl =  urlBase+"?access_key="+access_key+"&base=" + codigoMoneda + "&symbols=USD";

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet request = new HttpGet(apiUrl);

    try (CloseableHttpResponse response = httpClient.execute(request)) {

        int statusCode = response.getStatusLine().getStatusCode();
        String responseBody = EntityUtils.toString(response.getEntity());


        if (statusCode == 200) {

            // Convertir la respuesta JSON en un objeto InformacionPaisDTO
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(responseBody, InformacionMonedaDTO.class);

        } else {

            throw new ApiInformacionIPException("Error en la API: código de estado " + statusCode);
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }


}

}
