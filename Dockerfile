FROM openjdk:11-oracle

ENV NEXUS_URL=http://192.168.1.222:8081/repository/maven-releases/

EXPOSE 8089

WORKDIR /app

RUN curl -o achat-1.0.jar ${NEXUS_URL}/tn/esprit/rh/achat/1.0/achat-1.0.jar

CMD ["java", "-jar", "achat-1.0.jar"]
