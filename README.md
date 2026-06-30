markdown
# 📘 SpringBoot Question Manager  
**REST API and Web Application for Managing Test Questions**

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.9+-orange)
![Docker](https://img.shields.io/badge/Docker-Ready-0db7ed)
![Security](https://img.shields.io/badge/Security-JWT-red)
![CI](https://img.shields.io/badge/GitHub%20Actions-CI-success)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

Complete system for creating, editing, and managing test questions — true/false, single choice, and multiple choice — organized by topics. Includes a web interface, documented REST API, JWT security, automated tests, and Docker environment for production.

---

## 📑 Table of Contents

- Features  
- Tech Stack  
- Requirements  
- Quick Start  
- Profiles  
- Tests  
- Web Routes  
- REST API  
- Application Architecture  
- Data Model  
- Technologies Used  
- Roadmap  
- Contribution  
- Learnings  
- License  

---

## 🚀 Features

- Full CRUD for questions and topics  
- Three question types using JPA inheritance  
- Web interface with Thymeleaf + Bootstrap  
- REST API with DTOs and pagination  
- JWT authentication + form login  
- Automatic documentation with Swagger/OpenAPI  
- Dev/prod profiles with H2 and MySQL  
- Docker + docker-compose  
- Unit and integration tests  
- CI with GitHub Actions  

---

## 🛠️ Tech Stack

- Java 21  
- Spring Boot 4.1.0  
- Spring Data JPA  
- Spring Security + JWT  
- Thymeleaf + Bootstrap 5  
- SpringDoc OpenAPI 3.0.3  
- Docker / docker-compose  
- GitHub Actions  
- Lombok (DTOs)  

---

## 📦 Requirements

- JDK 21  
- Maven 3.9+  
- Docker + docker-compose (for production)  

---

## ⚡ Quick Start

### 🔹 Dev Profile (H2 in-memory)

```bash
./mvnw spring-boot:run
```

Application starts at:
- http://localhost:8080
- H2 Console: /h2-console
- Default user: admin / admin123

🔹 Prod Profile (MySQL + Docker)
```bash
docker compose up --build
```

🔧 Available Profiles
| Profile | Database | How to Activate |
| --- | --- | --- |
| dev | H2 | ``./mvnw ``spring-boot:run`` |
| prod | MySQL | ``SPRING_PROFILES_ACTIVE=prod ``./mvnw ``spring-boot:run`` |

🧪 Running Tests
```bash
./mvnw test
```

Includes:
- Unit tests (Mockito)
- Integration tests with @WebMvcTest
- Context load test

🌐 Web Application Routes
| Route | Description | Access |
| --- | --- | --- |
| ``/`` | Home page | Public |
| ``/preguntas`` | Paginated list | Public |
| ``/preguntas/nueva`` | Select question type | Public |
| ``/preguntas/nueva/verdadero-falso`` | Create true/false | Public |
| ``/preguntas/nueva/seleccion-unica`` | Create single choice | Public |
| ``/preguntas/nueva/seleccion-multiple`` | Create multiple choice | Public |
| ``/preguntas/editar/{id}`` | Edit question | Public |
| ``/preguntas/eliminar/{id}`` | Delete question | Public |
| ``/tematicas`` | Topic CRUD | Public |
| ``/login`` | Login | Public |
| ``/admin`` | Admin dashboard | ADMIN |
| ``/swagger-ui.html`` | API documentation | Public |
| ``/h2-console`` | H2 console | Public |


🔌 REST API

🔐 Authentication
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

📚 Endpoints
| Method | Route | Description |
| --- | --- | --- |
| GET | ``/api/preguntas`` | Paginated list + filters |
| GET | ``/api/preguntas/{id}`` | Get by ID |
| POST | ``/api/preguntas`` | Create question |
| PUT | ``/api/preguntas/{id}`` | Update question |
| DELETE | ``/api/preguntas/{id}`` | Delete question |


🏗️ Application Architecture
The application follows a layered architecture with clear separation of responsibilities:
- Controller – Entry layer (Web + REST).
- Service – Business logic.
- Repository – Data access (Spring Data JPA).
- Entity – Domain model using JPA inheritance (JOINED).
- Security – JWT, filters, authentication, authorization.
- DTOs + Mapper – Conversion between entities and transport objects.

🧱 Data Model
JPA inheritance (JOINED):
- Pregunta (abstract)
  - PreguntaVerdadeiroFalso
  - PreguntaSeleccionUnica
  - PreguntaSeleccionMultiple

Relationships:
- Tematica 1:N Pregunta
- Usuario for authentication (BCrypt)

🧩 Technologies Used
- Spring Boot – Modern and productive Java framework.
- JPA + Hibernate – Persistence with inheritance support.
- JWT – Stateless authentication for REST APIs.
- Thymeleaf – Server-side template engine.
- Docker – Reproducible environment and consistent deployment.
- GitHub Actions – Automated CI pipeline.

🧭 Roadmap

Planned improvements:
- Export questions (CSV/JSON)
- Quiz mode
- Performance statistics
- Internationalization (i18n)
- E2E tests with Selenium
- Redis cache
- API rate limiting
- OAuth2 support

🤝 Contribution

Contributions are welcome!
1. Fork the repository
2. Create a branch: feature/my-feature
3. Use Conventional Commits
4. Open a Pull Request

🎓 Learnings

This project helped reinforce:
- Layered architecture
- JPA inheritance
- JWT security
- OpenAPI documentation
- Unit and integration testing
- Docker deployment
- CI/CD with GitHub Actions

📝 License

This project was developed as a final evaluation exercise.