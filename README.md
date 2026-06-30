# 📘 SpringBoot Question Manager  
**API REST y Aplicación Web para la Gestión de Preguntas Tipo Test**

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.9+-orange)
![Docker](https://img.shields.io/badge/Docker-Ready-0db7ed)
![Security](https://img.shields.io/badge/Security-JWT-red)
![CI](https://img.shields.io/badge/GitHub%20Actions-CI-success)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

Sistema completo para la creación, edición y gestión de preguntas tipo test — verdadero/falso, selección única y selección múltiple — organizadas por temáticas. Incluye interfaz web, API REST documentada, seguridad con JWT, tests automatizados y entorno Docker para producción.

---

## 📑 Índice

- Funcionalidades  
- Stack Técnico  
- Requisitos Previos  
- Inicio Rápido  
- Perfiles  
- Tests  
- Rutas Web  
- API REST  
- Arquitectura  
- Modelo de Datos  
- Tecnologías Utilizadas  
- Roadmap  
- Contribución  
- Aprendizajes  
- Licencia  

---

## 🚀 Funcionalidades

- CRUD completo de preguntas y temáticas  
- Tres tipos de preguntas con herencia JPA  
- Interfaz web con Thymeleaf + Bootstrap  
- API REST con DTOs y paginación  
- Autenticación JWT + login por formulario  
- Documentación automática con Swagger/OpenAPI  
- Perfiles dev/prod con H2 y MySQL  
- Docker + docker-compose  
- Tests unitarios y de integración  
- CI con GitHub Actions  

---

## 🛠️ Stack Técnico

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

## 📦 Requisitos Previos

- JDK 21  
- Maven 3.9+  
- Docker + docker-compose (para producción)  

---

## ⚡ Inicio Rápido

### 🔹 Perfil Dev (H2 en memoria)

```bash
./mvnw spring-boot:run
```

La aplicación inicia en:
http://localhost:8080
Consola H2: /h2-console
Usuario por defecto: admin / admin123

🔹 Perfil Prod (MySQL + Docker)
docker compose up --build

🔧 Perfiles Disponibles
  | Perfil | Base de Datos | Cómo Activar |
  | --- | --- | --- |
  | dev | H2 | ``./mvnw ``spring-boot:run`` |
  | prod | MySQL | ``SPRING_PROFILES_ACTIVE=prod ``./mvnw ``spring-boot:run`` |

🧪 Ejecutar Tests
  ./mvnw test

Incluye:
  - Tests unitarios (Mockito)
  - Tests de integración con @WebMvcTest
  - Test de carga de contexto

🌐 Rutas de la Aplicación Web
  | Ruta | Descripción | Acceso |
  | --- | --- | --- |
  | ``/`` | Página inicial | Público |
  | ``/preguntas`` | Lista paginada | Público |
  | ``/preguntas/nueva`` | Seleccionar tipo | Público |
  | ``/preguntas/nueva/verdadero-falso`` | Crear V/F | Público |
  | ``/preguntas/nueva/seleccion-unica`` | Crear selección única | Público |
  | ``/preguntas/nueva/seleccion-multiple`` | Crear selección múltiple | Público |
  | ``/preguntas/editar/{id}`` | Editar pregunta | Público |
  | ``/preguntas/eliminar/{id}`` | Eliminar pregunta | Público |
  | ``/tematicas`` | CRUD de temáticas | Público |
  | ``/login`` | Inicio de sesión | Público |
  | ``/admin`` | Panel de administración | ADMIN |
  | ``/swagger-ui.html`` | Documentación API | Público |
  | ``/h2-console`` | Consola H2 | Público |


🔌 API REST
  🔐 Autenticación
  POST /api/auth/login
  Content-Type: application/json
  {
    "username": "admin",
    "password": "admin123"
  }

📚 Endpoints
  | Método | Ruta | Descripción |
  | --- | --- | --- |
  | GET | ``/api/preguntas`` | Listado paginado + filtros |
  | GET | ``/api/preguntas/{id}`` | Obtener por ID |
  | POST | ``/api/preguntas`` | Crear pregunta |
  | PUT | ``/api/preguntas/{id}`` | Actualizar pregunta |
  | DELETE | ``/api/preguntas/{id}`` | Eliminar pregunta |


🏗️ Arquitectura de la Aplicación
  La aplicación sigue una arquitectura en capas con separación clara de responsabilidades:
  - Controller – Entrada (Web + REST).
  - Service – Lógica de negocio.
  - Repository – Acceso a datos (Spring Data JPA).
  - Entity – Modelo de dominio con herencia JPA (JOINED).
  - Security – JWT, filtros y autorización.
  - DTOs + Mapper – Conversión entre entidades y objetos de transporte.

🧱 Modelo de Datos
  Herencia JPA (JOINED):
  - Pregunta (abstracta)
  - PreguntaVerdadeiroFalso
  - PreguntaSeleccionUnica
  - PreguntaSeleccionMultiple

Relaciones:
- Tematica 1:N Pregunta
- Usuario para autenticación (BCrypt)

🧩 Tecnologías Utilizadas
- Spring Boot – Framework moderno y productivo.
- JPA + Hibernate – Persistencia con soporte a herencia.
- JWT – Autenticación stateless para APIs REST.
- Thymeleaf – Motor de plantillas para aplicaciones web.
- Docker – Entorno reproducible y despliegue consistente.
- GitHub Actions – CI automatizado.

🧭 Roadmap
  Mejoras futuras:
  - Exportación de preguntas (CSV/JSON)
  - Modo quiz
  - Estadísticas de rendimiento
  - Internacionalización (i18n)
  - Tests E2E con Selenium
  - Cache con Redis
  - Rate limiting en la API
  - Soporte OAuth2

🤝 Contribución
  ¡Las contribuciones son bienvenidas!
  - Haz un fork
  - Crea una rama: feature/mi-feature
  - Usa Conventional Commits
  - Abre un Pull Request

🎓 Aprendizajes
  Este proyecto permitió reforzar:
  - Arquitectura en capas
  - Herencia JPA
  - Seguridad con JWT
  - Documentación OpenAPI
  - Tests unitarios e integración
  - Docker para despliegue
  - CI/CD con GitHub Actions

📝 Licencia
  Proyecto desarrollado como ejercicio de evaluación final.