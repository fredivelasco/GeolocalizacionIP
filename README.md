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
● Geolocalización de IPs: https://ip2country.info/
● Información de paises: http://restcountries.eu/
● Información sobre monedas: http://fixer.io/


## Análisis Inicial 





## Configuracion e Inicialización usando el Docker


## Configuración de aplicacion en un ambiente Linux 
1. ubicarse en el directorio donde se descargara la aplicación 

  cd /proyectos/aplicaciones

2. Clonar el proyecto

 git clone https://github.com/fredivelasco/GeolocalizacionIP.git

3. Ingresar a la carpeta del proyecto

 cd GeolocalizacionIP

 4. Contruir la imagen 

docker build -t geo-ip-app .

5. Ejecutar el contenedor

docker run -p 80:80 -p 8080:8080 -p 5432:5432 geo-ip-app



