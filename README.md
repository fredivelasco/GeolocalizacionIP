# GeolocalizacionIP
Challenge Tecnico Meli : Geolocalizacion de IPs

## Condiciones Iniciales

1. Construir una aplicación que dada una dirección IP, encuentre el país al que pertenece, y
muestre:
● El nombre y código ISO del país
● Los idiomas oficiales del país
● Hora(s) actual(es) en el país (si el país cubre más de una zona horaria, mostrar
todas)
● Distancia estimada entre Buenos Aires y el país, en km.
● Moneda local, y su cotización actual en dólares (si está disponible)

2. Almacenar  y mostrar  Estadisticas

3. Para resolver la información, pueden utilizarse las siguientes APIs públicas:

- Geolocalización de IPs: https://ip2country.info/

- Información de paises: http://restcountries.eu/

- Información sobre monedas: http://fixer.io/



## Análisis Inicial 

1. Se validan las Apis Sugeridas
    * en este punto se encuentra que ya no exiten por lo que se usan las siguientes

    ● Geolocalización de IPs: https://api.ipapi.com/api/

    ● Información de paises: https://api.countrylayer.com/v2/name/

        - Se implementa el consumo de la API, pero no se integra en las validaciones.


    ● Información sobre monedas: https://data.fixer.io/api/latest

    - Se implementa el consumo de la API, pero no se integra en las validaciones.

* Estas Apis tienen restingido el numero de peticiones, a 100 por mes.  es necesario inscribirse, y obtener el access_key

2. Con el fin de optimizar el consumo de las APIS, y el rendimiento de la aplicacion:

* se contempla almacenar la informacion de las ip consultadas en una base de Datos.
* Primero se Valida la información en la base de Datos (PostgreSQL)
    - Cuado se hace de esta manera se mejora la velocidad de respuesta
    - (Opcion de Mejora) Usar una estrategia de CACHE, con Redis (este quedo pendiente de implementar)
    - Exiten Datos Estaticos por ejemplo
      - Los Datos del País, como idioma, codigo de la moneda, TimeZone
    - Existen Datos Dinamicos
        - El valor de la moneda: En este caso se sugiere mantener en Cache el valor por las ultimas 24horas (Este punto No se implemento, la api de monedas no funciona adecuamente)   
        - La hora actual, se calcula teniendo en cuenta la ZoneTime del Pais.
* Como solucion se implementa un Microservicio en Spring Boot
    - Este tiene como objeto, controlar y solucionar de manera eficiente la disposicion de la información y su respectivo almacenamiento.

3. Se Configura y desarrolla un interfaz web ligera, que tiene como objeto:
    - Consultar la información de una IP, usando la APIRest desarrolalda
    - Consultar la información de las Estadisticas.


## Ejecucion de la aplicación en un ambiente Linux Ubuntu

1. ubicarse en el directorio donde se descargara la aplicación 

  cd /proyectos/aplicaciones

2. Clonar el proyecto

 git clone https://github.com/fredivelasco/GeolocalizacionIP.git

3. Ingresar a la carpeta del proyecto

 cd GeolocalizacionIP

 4. Ejecutar Orquestacion  

   docker compose up -d --build

5. Ingresar a la aplicacion 
* Usar la Ip de la maquina en la cual se ejecuta  la orquestacion
  http://192.168.20.120:80/
* Si es la maquina local
  http://localhost:80/
Esta desplegara la aplicacion frontEnd: que permite la consulta de IP, y tambien visualizar las estadisticas

## ApiRest y EndPoint

Si se usa una herramienta como postman se puede  consumir los servicios de la siguiente manera

### Consulta de la Informacion de la IP
GET : http://localhost:8080/api/consulta/108.159.152.1

- Cambiar localhost por la Ip donde se encuentra el container
- cambiar el valor de la ip segun se requiera

### Consulta de las Estadisticas de la apliacion

GET : http://localhost:8080/api/consulta/estadisticas
 -Cambiar localhost por la Ip donde se encuentra el container

## Oportunidades de mejora/ lecciones aprendidas
- Falto tiempo para hacer la configuración del Swagger, para la documentación de La Api y los dos EndPoints
- Se evaluo, otras maneras de alimentar la base de datos, con el fin de reducir aun mas el consumo de las apis.
- Falto configurar una herramienta de monitoreo, como Grafana o Prometheus.



