# Utilisez une image de base Java
FROM openjdk:11

  # Définissez le répertoire de travail dans le conteneur
WORKDIR /app

  # Copiez le fichier JAR de votre application dans le conteneur
COPY target/achat-1.0.jar achat-1.0.jar

  # Exposez le port sur lequel votre application Spring Boot écoute
EXPOSE 8083
  # Commande pour exécuter votre application Spring Boot
CMD ["java", "-jar", "achat-1.0.jar"]