# Use a base Java image
FROM eclipse-temurin:17-jdk

# Set the working directory
WORKDIR /app

# Copy the Spring Boot JAR to the container
COPY target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-Xmx256m", "-Xss512k", "-jar", "app.jar"]
