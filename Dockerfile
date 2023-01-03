FROM maven:3.8.6-openjdk-18 AS build
COPY src /backend-app/src
COPY pom.xml /backend-app
RUN mvn -f /backend-app/pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:17
COPY --from=build backend-app/target/rest-api.war /backend-app/app.war
ENTRYPOINT ["java","-jar","/backend-app/app.war"]
