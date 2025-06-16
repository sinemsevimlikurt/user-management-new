# User Management System

A comprehensive user management system developed with Spring Boot backend and React frontend, featuring JWT authentication, role-based authorization, and a modern user interface.

## Project Structure

The project is organized into two main directories:

- **`/backend`**: Spring Boot application with JWT authentication and RESTful APIs
- **`/frontend`**: React application with Tailwind CSS for modern UI

## Technologies Used

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Security** (JWT authentication)
- **Spring Data JPA** (database operations)
- **H2 Database** (for development environment)
- **Maven** (dependency management)
- **Lombok** (to reduce boilerplate code)

### Frontend
- **React** (functional components and hooks)
- **Vite** (fast development environment)
- **React Router** (page navigation)
- **Axios** (API requests)
- **Tailwind CSS** (modern and responsive design)
- **JWT** (authentication)

## Features

- User registration and login with JWT authentication
- Role-based authorization with Spring Security
- Protected routes requiring authentication
- User profile management
- Admin panel for user management
- Responsive UI with Tailwind CSS
- JWT token validation and automatic logout on expiration

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Node.js 14 or higher
- npm 6 or higher

### Backend Installation

1. Navigate to the backend directory:
   ```bash
   cd backend
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The backend will run at `http://localhost:8080`.

### Frontend Installation

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm run dev
   ```

The frontend will run at `http://localhost:5173`.

### H2 Database Console

The H2 console is available at `http://localhost:8080/h2-console` with the following default credentials:
- JDBC URL: `jdbc:h2:mem:userdb`
- Username: `sa`
- Password: `password`

## API Endpoints

### Authentication

- `POST /api/auth/signup` - Register a new user
- `POST /api/auth/signin` - Authenticate a user and get JWT token

### User Management

- `GET /api/users` - Get all users (Admin only)
- `GET /api/users/{id}` - Get user by ID (Admin or self)
- `PUT /api/users/{id}` - Update user (Admin or self)
- `DELETE /api/users/{id}` - Delete user (Admin only)

### Test Endpoints

- `GET /api/test/all` - Public access
- `GET /api/test/user` - For authenticated users
- `GET /api/test/mod` - For moderator users
- `GET /api/test/admin` - For admin users

## Default Admin User

The system creates a default admin user on startup:
- Username: `admin`
- Password: `admin123`
- Roles: `ROLE_ADMIN`

## Project Structure Details

### Backend Components

- **Controller**: Classes that manage API endpoints
  - `AuthController`: Authentication operations
  - `UserController`: User management
  - `TestController`: Test endpoints

- **Service**: Layer containing business logic
  - `AuthService`: Authentication operations
  - `UserService`: User operations

- **Repository**: Database operations
  - `UserRepository`: User database operations
  - `RoleRepository`: Role database operations

- **Model**: Database entities
  - `User`: User entity
  - `Role`: Role entity

- **Security**: Security configuration
  - JWT authentication
  - Role-based authorization

### Frontend Components

- **Components**: Reusable UI components
- **Pages**: Application pages
- **Services**: API requests and data operations
- **Utils**: Helper functions and constants

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
