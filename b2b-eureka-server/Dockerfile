FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/*.jar /app/b2b-eureka-server.jar
EXPOSE 8761
CMD ["java", "-jar", "b2b-eureka-server.jar"]