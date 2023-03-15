FROM openjdk:8-jdk-slim
LABEL maintainer=mwangli

COPY target/redis-demo-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java","-jar","app.jar"]