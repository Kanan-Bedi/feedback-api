# Feedback API — Spring Boot Project

This is a simple Spring Boot REST API for collecting user feedback, storing it in PostgreSQL, and demonstrating best practices for building, validating, and testing RESTful services.

---

## Features
- `POST /feedback` → Submit user feedback
- `GET /feedback` → Retrieve all feedback entries
- Input validation:
  - `name` must not be blank and must be at least 3 characters
  - `message` must not be blank and must be at least 10 characters
  - `email` must be valid format (`@`)

- Data is saved to a **PostgreSQL** database

---



## Technologies Used

- **Java 24**
- **Spring Boot 3.5.3**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **JUnit 5** (`@SpringBootTest`)
- **MockMvc** for integration tests
- **Mockito** for unit tests

---

## Project Structure
```bash
# Project Structure
feedback-api/
├── src/
│   ├── main/
│   │   ├── java/com/example/feedbackapi/
│   │   │   ├── entity/Feedback.java
│   │   │   ├── repository/FeedbackRepository.java
│   │   │   ├── service/FeedbackService.java
│   │   │   ├── controller/FeedbackController.java
│   │   │   └── FeedbackApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/com/example/feedbackapi/
│       │   ├── FeedbackApiApplicationTests.java
│       │   ├── FeedbackControllerTest.java
│       │   └── FeedbackServiceTest.java
├── pom.xml
└── README.md

---

## Setup & Run

### Clone the repo
```bash
git clone https://github.com/YOUR_USERNAME/feedback-api.git
cd feedback-api
```

### Configure PostgreSQL
- Create a database named feedback_db
- Update src/main/resources/application.properties with your credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/feedback_db
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Run Tests
```bash
mvn test
```

**Includes**:
- Controller tests for POST/GET and validation errors
- Service tests with Mockito
- Application context load test

### Example API Usage**
**POST feedback**:
```bash
curl -X POST http://localhost:8080/feedback \
  -H "Content-Type: application/json" \
  -d '{"name":"Kanan","email":"kanan@example.com","message":"This is my feedback!"}'
```

**GET all feedback**:
```bash
curl http://localhost:8080/feedback
```
