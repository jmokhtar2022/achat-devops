# Use a specific OpenJDK 11 image
FROM openjdk:11-oracle

# Set the Nexus URL as an environment variable
ENV NEXUS_URL=http://192.168.1.222:8081/repository/maven-releases/

# Expose the application's port
EXPOSE 8089

# Set the working directory
WORKDIR /app

# Download the JAR file from Nexus
RUN curl -o achat-1.0.jar ${NEXUS_URL}/tn/esprit/rh/achat/1.0/achat-1.0.jar

# Specify the command to run the application
CMD ["java", "-jar", "achat-1.0.jar"]
