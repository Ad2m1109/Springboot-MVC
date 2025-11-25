# Spring Boot MVC Film Application

This is a simple Spring Boot MVC application for managing films and categories.

## Features

*   **Film Management**:
    *   List all films.
    *   Add new films.
    *   Delete existing films.
*   **Category Management**:
    *   Categories are used to classify films.

## Technologies Used

*   **Spring Boot**: Framework for building the application.
*   **Spring Data JPA**: For database interaction.
*   **Thymeleaf**: Templating engine for the view layer.
*   **MySQL**: Database to store film and category information.
*   **Lombok**: To reduce boilerplate code (getters, setters, constructors).
*   **Maven**: Build automation tool.

## Project Structure

The project follows a **layered Spring Boot MVC architecture** with proper separation of concerns:

*   `src/main/java/com/example/springbootMVC/`:
    *   `controller/`: Contains `FilmController.java` for handling HTTP requests and responses
    *   `service/`: Service layer interfaces and implementations
        *   `FilmService.java` & `CategoryService.java`: Service interfaces
        *   `impl/FilmServiceImpl.java` & `impl/CategoryServiceImpl.java`: Business logic implementations
    *   `model/`: JPA entities (`Film.java` and `Category.java`)
    *   `repository/`: Spring Data JPA repositories (`FilmRepository.java` and `CategoryRepository.java`)
    *   `SpringbootMvcApplication.java`: Main application entry point with data seeder
*   `src/main/resources/`:
    *   `application.properties`: Application configuration (database, JPA settings)
    *   `templates/`: Thymeleaf HTML templates (`film.html`, `addfilm.html`)
    *   `static/`: Contains static resources (e.g., CSS, JavaScript).
*   `pom.xml`: Maven project configuration file.

## Setup and Run

1.  **Prerequisites**:
    *   Java 21 or higher.
    *   Maven.
    *   MySQL database.

2.  **Database Configuration**:
    *   Create a MySQL database.
    *   Update `src/main/resources/application.properties` with your database connection details:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name?createDatabaseIfNotExist=true
        spring.datasource.username=your_username
        spring.datasource.password=your_password
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
        ```

3.  **Build the Application**:
    ```bash
    mvn clean install
    ```

4.  **Run the Application**:
    ```bash
    mvn spring-boot:run
    ```
    The application will start on `http://localhost:8080`.

## Usage

*   Access the film list: `http://localhost:8080/films`
*   Add a new film: `http://localhost:8080/addfilm`
*   Delete a film: (Click the delete button on the film list page)

## Initial Data

The `SpringbootMvcApplication.java` contains a `CommandLineRunner` bean that automatically populates the database with initial film and category data on application startup. The seeder uses the service layer for proper business logic validation.

```java
@Bean
CommandLineRunner initData(FilmService filmService, CategoryService categoryService) {
    return args -> {
        // Create categories
        Category c1 = new Category("Comedie");
        Category c2 = new Category("Horreur");
        Category c3 = new Category("Action");
        categoryService.saveCategory(c1);
        categoryService.saveCategory(c2);
        categoryService.saveCategory(c3);
        
        // Create films
        Film f1 = new Film("Harvard", "Film des années 60", 1962, c1);
        Film f2 = new Film("Malcolm", "Film des années 80", 1980, c2);
        Film f3 = new Film("Brusly", "Film des années 90", 1990, c3);
        filmService.saveFilm(f1);
        filmService.saveFilm(f2);
        filmService.saveFilm(f3);
    };
}
```

## Architecture

This project follows **Spring Boot MVC best practices**:

*   **Controller Layer**: Handles HTTP requests/responses
*   **Service Layer**: Contains business logic and validation
*   **Repository Layer**: Handles database operations
*   **Transaction Management**: `@Transactional` at service level
*   **Logging**: SLF4J for structured logging
*   **Dependency Injection**: Constructor-based injection
