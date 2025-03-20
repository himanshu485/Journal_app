# Smart Journal Manager - Spring Boot API

## Overview
Smart Journal Manager is a Spring Boot-based REST API application designed to help users manage their journal entries efficiently. It includes authentication, user management, and a system to analyze user sentiments and send scheduled emails based on journal content.

## Features
- User Authentication & Authorization with **Spring Security & JWT**
- User & Admin Role Management
- Journal Entry CRUD Operations
- Sentiment Analysis & Email Scheduling
- API Caching with **Redis**
- Kafka for Event-driven Messaging
- MongoDB with JPA for Data Persistence
- Unit & Integration Testing with **JUnit & Mockito**

## Tech Stack
- **Spring Boot** - Framework for building microservices
- **Spring Security** - Authentication and authorization
- **JWT (JSON Web Token)** - Secure user authentication
- **JUnit & Mockito** - Testing framework
- **Kafka** - Event-driven architecture
- **Redis** - Caching layer for performance improvement
- **MongoDB & JPA** - NoSQL database for data storage
- **Scheduler** - Automatically sends emails weekly based on journal sentiment analysis

## API Endpoints

### 1️⃣ Admin APIs (`/admin/`)
| HTTP Method | Endpoint | Description |
|------------|----------|-------------|
| **GET** | `/admin/all-users` | Fetch all users |
| **POST** | `/admin/create-new-admin` | Create a new admin user |
| **GET** | `/admin/clear-app-cache` | Clear the application cache |

### 2️⃣ Journal Entry APIs (`/journal/`)
| HTTP Method | Endpoint | Description |
|------------|----------|-------------|
| **GET** | `/journal/` | Get all journal entries of the authenticated user |
| **GET** | `/journal/id/{myId}` | Get a journal entry by ID |
| **POST** | `/journal/` | Create a new journal entry |
| **PUT** | `/journal/id/{myId}` | Update a journal entry by ID |
| **DELETE** | `/journal/id/{myId}` | Delete a journal entry by ID |

### 3️⃣ Public APIs (`/public/`)
| HTTP Method | Endpoint | Description |
|------------|----------|-------------|
| **GET** | `/public/health-check` | Check if the application is running |
| **POST** | `/public/signup` | Sign up a new user |
| **POST** | `/public/login` | Authenticate a user and generate a JWT token |

## Installation & Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/smart-journal-manager.git
   cd smart-journal-manager
   ```
2. Configure application properties in `src/main/resources/application.yml`.
3. Build the project:
   ```sh
   mvn clean install
   ```
4. Run the application:
   ```sh
   mvn spring-boot:run
   ```
5. The API will be available at `http://localhost:8080/`.

## Running Tests
Run unit and integration tests using:
```sh
mvn test
```

## Future Enhancements
- Add GraphQL support
- Implement AI-based sentiment analysis for better email recommendations
- Improve admin dashboard with analytics

---
Developed with ❤️ using **Spring Boot & Modern Technologies**

