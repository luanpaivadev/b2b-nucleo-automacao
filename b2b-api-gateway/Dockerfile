FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/*.war /app/b2b-api-gateway.war
EXPOSE 8080
CMD ["java", "-jar", "b2b-api-gateway.war"]