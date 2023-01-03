FROM openjdk:17
WORKDIR /backend-app

RUN groudapp appuser && useradd -g appuser -d /backend-app -M appuser
RUN chown -R appuser:appuser /backend-app

USER appuser:appuser

ARG WAR_FILE=target/rest-api.war
COPY ${WAR_FILE} app.war
ENTRYPOINT ["java","-jar","/backend-app/app.war"]
