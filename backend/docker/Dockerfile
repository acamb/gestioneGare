FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
RUN mkdir /db
RUN chown spring:spring /db
USER spring:spring
ARG JAR_FILE=./gestionegare-*.jar
COPY ${JAR_FILE} gestioneGare.jar
ENTRYPOINT ["java","-jar","/gestioneGare.jar"]