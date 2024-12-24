FROM openjdk:17-ea-33-slim
VOLUME /tmp
COPY build/libs/service-discovery-1.0.jar discovery-service.jar
ENTRYPOINT ["java","-jar","discovery-service.jar"]