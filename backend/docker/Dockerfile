FROM openjdk:17-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
RUN mkdir /db
RUN chown spring:spring /db
USER spring:spring
ARG JAR_FILE=./gestionegare-*.jar
ARG CONFIG_FILE=./application.properties
COPY ${JAR_FILE} gestioneGare.jar
COPY ${CONFIG_FILE} application.properties
ENTRYPOINT ["java","-jar","/gestioneGare.jar"]