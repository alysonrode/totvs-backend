FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -Dmaven.test.skip

FROM openjdk:17-oracle
COPY --from=build /app/target/totvs-api-0.0.1.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","/totvs-api-0.0.1.jar"]