FROM postgres:13

# Definir variables de entorno para PostgreSQL
ENV POSTGRES_DB=geoip_db
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=c0lombia

# Copiar el script de inicialización de la base de datos
COPY Init_DataBase.sql /docker-entrypoint-initdb.d/

# Exponer el puerto 5432 para PostgreSQL
EXPOSE 5432