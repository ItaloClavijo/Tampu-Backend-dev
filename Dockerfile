# Usa una imagen base con OpenJDK 17 de tipo Debian
FROM openjdk:17-buster

# MAINTAINER (opcional)
MAINTAINER ItaloClavijo

# Instalar curl utilizando apt-get
RUN apt-get update && apt-get install -y curl

# Descargar el archivo .jar desde Google Drive
RUN curl -L -o /Tampu-Backend.jar "https://drive.google.com/uc?export=download&id=1WKZq6AONJo_ZgvjxX9b1xZtUKODHiIgk"

# Exponer el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/Tampu-Backend.jar"]
