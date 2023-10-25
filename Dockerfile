FROM openjdk:11

WORKDIR /app

COPY target/achat-1.0.jar achat-1.0.jar

EXPOSE 8083

CMD ["java", "-jar", "achat-1.0.jar"]
