FROM openjdk:17-ea-33-slim
VOLUME /tmp
COPY apiEncryptionKey.jks apiEncryptionKey.jks
COPY build/libs/config-service-1.0.jar config-service.jar
ENTRYPOINT ["java","-jar","config-service.jar"]