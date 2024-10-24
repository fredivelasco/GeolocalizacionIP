package com.developer.geoips.external.client;

import com.developer.geoips.exception.ApiInformacionIPException;
import com.developer.geoips.external.dto.InformacionIPDTO;
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
public class InformacionIPApiClient {

    @Value("${api.informacionIp.url}")
    private String urlBase;

    @Value("${api.informacionIp.access_key}")
    private String access_key;

public InformacionIPDTO obtenerInformacionIP(String ipAddress) throws IOException{
    String apiUrl =  urlBase+ipAddress +"?access_key="+access_key+"&language=es";

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet request = new HttpGet(apiUrl);

    try (CloseableHttpResponse response = httpClient.execute(request)) {

        int statusCode = response.getStatusLine().getStatusCode();
        String responseBody = EntityUtils.toString(response.getEntity());


        if (statusCode == 200) {
            //System.out.println("FJVV Consulta a la API OK");
            // Convertir la respuesta JSON en un objeto InformacionIPDTO
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(responseBody, InformacionIPDTO.class);

        } else {

            throw new ApiInformacionIPException("Error en la API: código de estado " + statusCode);
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }


}

}
