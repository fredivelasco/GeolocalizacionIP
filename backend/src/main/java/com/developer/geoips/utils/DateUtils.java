package com.developer.geoips.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String fechaActualFormateada(){
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return fechaActual.format(formato);
    }

    public static String horaActualUTC(String offSet) {

        // offSet debe ser en el formato "+02:00",  el valor  en la tabla  UTC -04:00
        String offsetUTC = offSet.replace("UTC", "");

        LocalDateTime localDateTime = LocalDateTime.now();

        OffsetDateTime utcDateTime = localDateTime.atOffset(ZoneOffset.UTC);


        ZonedDateTime ahoraUTC = ZonedDateTime.now(ZoneId.of("UTC"));

        ZonedDateTime ahoraEnUTC_offset = ahoraUTC.withZoneSameInstant(ZoneOffset.of(offsetUTC));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");


        return  ahoraEnUTC_offset.format(formatter);

    }


}
