# Comments API

A Spring Boot REST API for managing comments with CRUD operations, search functionality, and API documentation.

## Project Description

This project is a RESTful API built with Spring Boot that allows managing comments. It provides endpoints for retrieving comments, searching comments by username and date, creating new comments, updating existing comments, and deleting comments. The API uses MySQL for data persistence and includes error handling, API documentation generated with SpringDoc, and unit and integration tests.

## Features

- CRUD operations for comments
- Search comments by username and date
- API documentation with SpringDoc
- Error handling and appropriate HTTP status codes
- Unit and integration tests

## Prerequisites

- Java 11 or higher
- Maven
- MySQL

## Setup

1. Clone the repository:
   ```
   git clone https://github.com/your-username/comments-api.git
   cd comments-api
   ```

2. Configure the database:
   - Create a MySQL database for the project
   - Update the `application.properties` file with your database connection details

3. Build the project:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

The application will start running at `http://localhost:8080`.

## API Endpoints

- `GET /api/v2/comments`: Retrieve all comments
- `GET /api/v2/comments/search?username={username}`: Search comments by username
- `GET /api/v2/comments/search?date={date}`: Search comments by date
- `POST /api/v2/comments`: Create a new comment
- `PUT /api/v2/comments/{id}`: Update an existing comment
- `DELETE /api/v2/comments/{id}`: Delete a comment

## API Documentation

The API documentation is generated using SpringDoc and can be accessed at the following URLs:
- API Docs: `http://localhost:8080/api-docs`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

## Testing

The project includes unit tests and integration tests. To run the tests, use the following command:
```
mvn test
```

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).