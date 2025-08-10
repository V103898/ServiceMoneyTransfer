
LABEL authors="CTACOB"
ADD build/libs/KubernetesTestContainers.jar myapp.jar
EXPOSE 8080

FROM openjdk:21-jdk-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
CMD ["java", "-jar", "app.jar"]
