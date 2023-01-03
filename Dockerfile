FROM openjdk:17
WORKDIR /backend-app
ARG WAR_FILE=target/rest-api.war
COPY ${WAR_FILE} app.war
ENTRYPOINT ["java","-jar","/backend-app/app.war"]
