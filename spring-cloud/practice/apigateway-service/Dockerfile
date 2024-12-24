FROM openjdk:17-ea-33-slim
VOLUME /tmp
COPY build/libs/apigateway-service-1.0.jar apigateway-service.jar
ENTRYPOINT ["java","-jar","apigateway-service.jar"]