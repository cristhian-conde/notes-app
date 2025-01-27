# Notes Application

The **Notes Application** is a robust and scalable Spring Boot-based application designed for managing notes, tags, and user preferences. It provides a secure and efficient way for users to create, organize, and manage their notes with features like tagging, archiving, and advanced search capabilities.

---

## Features

- **User Management**:
  - User registration and authentication (JWT-based).
  - Secure password storage using BCrypt.
  - Role-based access control (currently supports a single role: `USER`).

- **Notes Management**:
  - Create, read, update, and delete (CRUD) notes.
  - Archive and unarchive notes.
  - Search notes by title, content, or tags.
  - Pagination and sorting support for listing notes.

- **Tags Management**:
  - Create, read, update, and delete (CRUD) tags.
  - Associate tags with notes.
  - Filter notes by tags.

- **User Preferences**:
  - Save and retrieve user-specific filter preferences.
  - Store search and filter states for a personalized experience.

- **Security**:
  - JWT-based authentication.
  - Role-based access control.
  - Secure endpoints with Spring Security.

- **Database**:
  - Uses **H2** in-memory database for development.
  - Supports **PostgreSQL** or **MySQL** for production.

---

## Technologies Used

- **Backend**:
  - Spring Boot 3.x
  - Spring Data JPA
  - Spring Security
  - JWT (JSON Web Tokens)
  - H2 Database (for development)
  - PostgreSQL/MySQL (for production)

- **Tools**:
  - Docker (for containerization)
  - Maven (for dependency management)
  - Swagger/OpenAPI (for API documentation)

---

## Prerequisites

Before running the application, ensure you have the following installed:

- **Java Development Kit (JDK)**: JDK 17 or higher (Eclipse Temurin or Amazon Corretto recommended).
- **Maven**: For building the project.
- **Docker**: For containerized deployment (optional).
- **PostgreSQL/MySQL**: For production (optional).

---

## Running the Application

### 1. **Local Development**

#### Build the Project
```bash
mvn clean install
```

#### Run the Application
```bash
mvn spring-boot:run
```

The application will start on port `8080`. You can access it at:
```
http://localhost:8080
```

#### Access Swagger UI
To explore the API documentation, visit:
```
http://localhost:8080/swagger-ui.html
```

---

### 2. **Docker Deployment**

#### Build the Docker Image
```bash
docker build -t notes-app .
```

#### Run the Docker Container
```bash
docker run -p 8080:8080 notes-app
```

The application will be available at:
```
http://localhost:8080
```

---

### 3. **Environment Variables**

For production, configure the following environment variables in a `.env` file or directly in your deployment environment:

```bash
# Database Configuration
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/notesdb
SPRING_DATASOURCE_USERNAME=your_db_user
SPRING_DATASOURCE_PASSWORD=your_db_password

# JWT Configuration
JWT_SECRET=your_jwt_secret_key
JWT_EXPIRATION=86400000 # 24 hours in milliseconds

# Swagger Configuration (optional)
SPRINGDOC_SWAGGER_UI_ENABLED=true
```

---

## API Endpoints

### Authentication
- **Register User**: `POST /api/auth/register`
- **Login**: `POST /api/auth/login`

### Notes
- **Create Note**: `POST /api/notes`
- **Get Note by ID**: `GET /api/notes/{id}`
- **Get All Notes**: `GET /api/notes`
- **Update Note**: `PUT /api/notes/{id}`
- **Delete Note**: `DELETE /api/notes/{id}`

### Tags
- **Create Tag**: `POST /api/tags`
- **Get Tag by ID**: `GET /api/tags/{id}`
- **Get All Tags**: `GET /api/tags`
- **Update Tag**: `PUT /api/tags/{id}`
- **Delete Tag**: `DELETE /api/tags/{id}`

---

## Database Schema

The application uses the following entities:

- **Users**: Stores user information.
- **Notes**: Stores notes with title, content, and archive status.
- **Tags**: Stores tags associated with notes.
- **UserFilterPreferences**: Stores user-specific filter preferences.

---

## Contact

For questions or feedback, please contact:
- **Email**: [cristhian.conde.ajururo@gmail.com](mailto:cristhian.conde.ajururo@gmail.com)
- **GitHub**: [https://github.com/cristhian-conde/](https://github.com/cristhian-conde/)

---

## Acknowledgments

- Spring Boot and Spring Security for providing a robust framework.
- Swagger for API documentation.
- Docker for containerization support.

---

Enjoy using the **Notes Application**! 🚀