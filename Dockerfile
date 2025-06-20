# Usa la imagen base de OpenJDK 17 con Debian Buster
FROM openjdk:17-buster

# Instala wget para poder manejar la descarga con confirmación de Google Drive
RUN apt-get update && apt-get install -y wget

# Descarga el JAR desde Google Drive con manejo de confirmación
RUN FILE_ID=1WKZq6AONJo_ZgvjxX9b1xZtUKODHiIgk && \
    CONFIRM=$(wget --quiet --save-cookies /tmp/cookies.txt \
        --keep-session-cookies --no-check-certificate \
        "https://docs.google.com/uc?export=download&id=${FILE_ID}" -O- | \
        sed -rn 's/.*confirm=([0-9A-Za-z_]+).*/\1/p') && \
    wget --load-cookies /tmp/cookies.txt \
        "https://docs.google.com/uc?export=download&confirm=${CONFIRM}&id=${FILE_ID}" \
        -O /Tampu-Backend.jar && \
    rm -rf /tmp/cookies.txt

# Comando por defecto al iniciar el contenedor
CMD ["java", "-jar", "/Tampu-Backend.jar"]

