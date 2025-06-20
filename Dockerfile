FROM openjdk:17
MAINTAINER ItaloClavijo
COPY target/Tampu-Backend-0.0.1-SNAPSHOT.jar Tampu-Backend.jar
ENTRYPOINT ["java", "-jar","/Tampu-Backend.jar"]