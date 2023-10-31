FROM openjdk:11-oracle

ENV NEXUS_URL=http://192.168.1.14:8081/repository/maven-releases/
ENV GROUP_ID=tn/esprit/rh
ENV ARTIFACT_ID=achat
ENV ARTIFACT_VERSION=1.0
ENV ARTIFACT_PACKAGING=jar

ENV MANAGEMENT_ENDPOINT_PROMETHEUS_ENABLED=true

ENV MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE=prometheus
EXPOSE 8089

RUN curl -o /${ARTIFACT_ID}-${ARTIFACT_VERSION}.${ARTIFACT_PACKAGING} ${NEXUS_URL}${GROUP_ID}/${ARTIFACT_ID}/${ARTIFACT_VERSION}/${ARTIFACT_ID}-${ARTIFACT_VERSION}.${ARTIFACT_PACKAGING}

CMD java -jar -Dspring.profiles.active=actuator,prod /${ARTIFACT_ID}-${ARTIFACT_VERSION}.${ARTIFACT_PACKAGING}




