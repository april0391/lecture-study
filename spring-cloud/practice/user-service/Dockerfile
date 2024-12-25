FROM openjdk:17-ea-33-slim
VOLUME /tmp
COPY build/libs/user-service-1.0.jar user-service.jar
ENTRYPOINT ["java","-jar","user-service.jar"]