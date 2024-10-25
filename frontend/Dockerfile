FROM nginx:alpine

# Copiar los archivos compilados de React a la carpeta de Nginx
COPY build/ /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
# Exponer el puerto 80 para Nginx
EXPOSE 80