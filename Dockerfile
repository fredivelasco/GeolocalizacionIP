# Etapa 1: Nginx para servir la aplicación ReactJS
FROM nginx:alpine AS nginx-frontend

# Copiar el build de React en la carpeta de Nginx
COPY frontend/build /usr/share/nginx/html

# Exponer el puerto 80 para servir la aplicación React
EXPOSE 80

# Etapa 2: Aplicación Spring Boot (Java 17)
FROM openjdk:17-jdk-slim AS spring-backend

# Crear un directorio de trabajo
WORKDIR /app

# Copiar el JAR de la aplicación Spring Boot
COPY backend/target/*.jar /app/app.jar

# Exponer el puerto 8080 para la API de Spring Boot
EXPOSE 8080

# Etapa 3: PostgreSQL
FROM postgres:13 AS postgres-db

# Definir variables de entorno para PostgreSQL
ENV POSTGRES_DB=geoip_db
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres

# Crear un volumen para la persistencia de datos
VOLUME ["/var/lib/postgresql/data"]

# Copiar el script de inicialización de la base de datos (si tienes un archivo DDL)
COPY database/init.sql /docker-entrypoint-initdb.d/

# Exponer el puerto 5432 para PostgreSQL
EXPOSE 5432

# Etapa final: Unificar servicios
FROM openjdk:17-jdk-slim AS final

# Copiar la configuración de Nginx
COPY --from=nginx-frontend /usr/share/nginx/html /usr/share/nginx/html

# Copiar la aplicación Spring Boot
COPY --from=spring-backend /app/app.jar /app/app.jar

# Copiar PostgreSQL (si es necesario para correr en el mismo contenedor)
COPY --from=postgres-db /usr/lib/postgresql /usr/lib/postgresql
COPY --from=postgres-db /var/lib/postgresql /var/lib/postgresql

# Exponer los puertos para Nginx, Spring Boot y PostgreSQL
EXPOSE 80 8080 5432

# Comando final: Ejecutar Nginx, PostgreSQL y Spring Boot
CMD service postgresql start & java -jar /app/app.jar & nginx -g "daemon off;"
