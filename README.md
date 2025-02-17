# SportyShoes Project

## Overview
SportyShoes is a web application designed to manage an online shoe store. It includes features for managing products, categories, users, and orders. The application is built using Java and Spring Boot, with a focus on simplicity and ease of use.

## Technologies Used
- **Java 17**: The primary programming language used for the backend.
- **Spring Boot 3.4.1**: A framework that simplifies the development of Java applications by providing a comprehensive infrastructure.
    - **Spring Data JPA**: For database interactions.
    - **Spring Security**: For authentication and authorization.
    - **Spring Web**: For building web applications.
    - **Spring Boot DevTools**: For development and testing.
    - **Spring Boot Starter Thymeleaf**: For server-side rendering of web pages.
- **H2 Database**: An in-memory database used for testing purposes and project submission.
- ~~**SQLite**~~: Initially used for persistent storage before switching to H2.
- **Thymeleaf**: A modern server-side Java template engine for web and standalone environments.
- **Maven**: A build automation tool used for managing project dependencies and building the project.
- **JUnit 5**: For unit testing the application.

## Why These Technologies?
- **Java**: A robust, object-oriented programming language that is widely used for building enterprise-level applications.
- **Spring Boot**: Provides a comprehensive framework that simplifies the development of Java applications, with built-in support for various functionalities like data access, security, and web development.
- **H2 Database**: Ideal for testing and project submission due to its in-memory nature, which allows for quick setup and teardown.
- **Thymeleaf**: Allows for natural templating, making it easier to create dynamic web pages.
- **Maven**: Simplifies dependency management and project building, ensuring consistency across different environments.
- **JUnit 5**: Provides a powerful and flexible testing framework to ensure the reliability of the application.

## Features
- **User Management**: Update user information.
- **Product Management**: Add categories to products.
- **Search Functionality**: Simple front-end search for products, users, and categories in the admin panel.
- **Validation**: No validation yet on user input, but it should be handled in the service layer to ensure business logic integrity and in the controller layer to provide immediate feedback to users.

## How to Login
1. **Start the Application**: Run the Spring Boot application using your IDE or by executing the `mvn spring-boot:run` command in the terminal.
2. **Access the Login Page**: Open your web browser and navigate to `http://localhost:8080/login`.
3. **Enter Credentials**: Use the following default credentials to log in:
    - **Username**: `test@test.com`
    - **Password**: `javaproject`
4. **Submit**: Click the login button to access the application.

## Project Structure
- `src/main/java/com/web/sportyShoes`: Contains the main application code, including models, repositories, services, and controllers.
- `src/main/resources`: Contains application properties and static resources like HTML templates.
- `src/test/java/com/web/sportyShoes`: Contains unit tests for the application.

## Getting Started
1. **Clone the Repository**:
   ```sh
   git clone https://github.com/julian-jackson_voda/sportyShoes.git