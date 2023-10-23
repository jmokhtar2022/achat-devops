FROM openjdk:11

ARG NEXUS_URL=http://localhost:8081/repository/maven-releases/
ARG GROUP_ID=tn.esprit.rh
ARG ARTIFACT_ID=achat
ARG ARTIFACT_VERSION=1.0
ARG ARTIFACT_PACKAGING=jar


EXPOSE 8089

RUN curl -o /${ARTIFACT_ID}-${ARTIFACT_VERSION}.${ARTIFACT_PACKAGING} ${NEXUS_URL}${GROUP_ID}/${ARTIFACT_ID}/${ARTIFACT_VERSION}/${ARTIFACT_ID}-${ARTIFACT_VERSION}.${ARTIFACT_PACKAGING}

ENTRYPOINT ["java","jar","/${ARTIFACT_ID}-${ARTIFACT_VERSION}.${ARTIFACT_PACKAGING}"]

