FROM openjdk:21
ENV IMG_PATH=/img
EXPOSE 8080
RUN mkdir -p ${IMG_PATH}
COPY target/libreria-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]