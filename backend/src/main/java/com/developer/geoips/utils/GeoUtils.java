package com.developer.geoips.utils;

import com.developer.geoips.dto.DistanciaDTO;

public class GeoUtils {

    final static double LATITUD_BUENOS_AIRES = -34.61315;
    final static double LONGITUD_BUENOS_AIRES = -58.37723;

    public static DistanciaDTO calcularDistanciaBuenosAires(double latitudPais, double longitudPais) {

        DistanciaDTO distanciaDTO = new DistanciaDTO();



        // Coordenadas de Pais
        //double latitudPais = Double.parseDouble(latitudPaisAlfa);
        //double longitudPais = Double.parseDouble(logitudPaisAlfa);

        // Calcular la distancia usando la fórmula de Haversine
        double distancia = haversine(LATITUD_BUENOS_AIRES, LONGITUD_BUENOS_AIRES, latitudPais, longitudPais);

        distanciaDTO.setDistancia(distancia);
        distanciaDTO.setLatitudOrigen(LATITUD_BUENOS_AIRES);
        distanciaDTO.setLongitudOrigen(LONGITUD_BUENOS_AIRES);
        distanciaDTO.setLatitudDestino(latitudPais);
        distanciaDTO.setLongitudDestino(longitudPais);

        return distanciaDTO;
    }




    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int RADIO_TIERRA_KM = 6371; // Radio promedio de la Tierra en km

        double dLat = Math.toRadians(lat2 - lat1);  // Diferencia de latitudes en radianes
        double dLon = Math.toRadians(lon2 - lon1);  // Diferencia de longitudes en radianes
        double lat1Rad = Math.toRadians(lat1);  // Convertir latitud 1 a radianes
        double lat2Rad = Math.toRadians(lat2);  // Convertir latitud 2 a radianes

        // Aplicar fórmula de Haversine
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distancia final en km
        return RADIO_TIERRA_KM * c;
    }


    public static String coordenadasBuenosAires(Double latitudDestino, Double longitudDestino) {

     String coordenadas =    " (" + Utilities.doubleToString(LATITUD_BUENOS_AIRES) +", "+Utilities.doubleToString(LONGITUD_BUENOS_AIRES) +") a ("+  Utilities.doubleToString(latitudDestino) + ", "+Utilities.doubleToString(longitudDestino) +")";

     return coordenadas;



    }
}
