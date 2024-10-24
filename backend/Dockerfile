FROM openjdk:17-jdk-slim

# Crear un directorio de trabajo
WORKDIR /app

# Copiar el JAR de la aplicación Spring Boot
COPY target/*.jar /app/app.jar

# Exponer el puerto 8080 para la aplicación Spring Boot
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]