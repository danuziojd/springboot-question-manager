# AGENTS.md

## Quick start

```bash
./mvnw compile           # build
./mvnw test              # run tests (single context-load test exists)
./mvnw spring-boot:run   # dev server on :8080
./mvnw package           # produce executable JAR
```

## Project anatomy

- **Spring Boot 4.1.0** (Boot 4.x = Spring Framework 7.x — newer API surface, do not assume Boot 3.x patterns)
- **Java 17**, single-module Maven, uses `mvnw` wrapper
- **Package**: `com.unaempresa.ejercicioavaluacionfinal`
- Layers: `controller/`, `entity/`, `service/` (no `repository/` package — repos live in `entity/`)
- Entrypoint: `EjercicioAvaluacionFinalApplication.java`
- Thymeleaf templates with fragment includes (`fragments/header`, `fragments/menu`); Bootstrap CSS/JS vendored in `static/`

## State of the code

**Early-stage project.** Only one controller (`HomeController` → `GET /` → `index.html`). The menu references routes `/preguntas`, `/tematicas`, `/admin` that have no controller yet. Only entity relationship so far: `Pregunta` @ManyToOne → `Tematica`.

## Notable quirks

- **Boot 4.x starter artifact IDs differ** from Boot 3.x/2.x: e.g., `spring-boot-starter-webmvc` (not `spring-boot-starter-web`), `spring-boot-starter-validation`, separate `spring-boot-starter-*-test` starters instead of a single `spring-boot-starter-test`
- **`spring-boot-h2console`** dependency (non-standard starter name, present in pom.xml)
- **No Lombok annotations** in entities — getters/setters are hand-written
- **`application.properties`** has only `spring.application.name`; H2 in-memory defaults apply (H2 console at `/h2-console`)
- MySQL connector included as runtime dependency but no MySQL config present in repo
- One test: `@SpringBootTest` context-load test
- No CI, no lint/formatter config, no snapshot tests