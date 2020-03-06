FROM --platform=linux/arm/v7 arm32v7/adoptopenjdk:11-hotspot
RUN addgroup --system spring && adduser --system spring --group
RUN mkdir /db
RUN chown spring:spring /db
USER spring:spring
ARG JAR_FILE=./gestionegare-*.jar
COPY ${JAR_FILE} gestioneGare.jar
ENTRYPOINT ["java","-jar","/gestioneGare.jar"]