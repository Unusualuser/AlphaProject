FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} alpha.jar
ENTRYPOINT ["java","-jar","/alpha.jar"]