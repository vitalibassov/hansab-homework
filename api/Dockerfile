FROM openjdk:8-alpine AS build
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY . .
RUN ./gradlew build

FROM openjdk:8-alpine
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=build $APP_HOME/build/libs/carviewer-1.0.jar .
EXPOSE 8080
CMD ["java","-jar", "carviewer-1.0.jar"]