# --- ETAPA 1: build ---
# Usamos una imagen con el JDK completo (necesario para compilar) y Maven preinstalado.
FROM maven:3.9-eclipse-temurin-25 AS build

WORKDIR /build

# Copiamos primero solo el pom.xml para aprovechar el cache de Docker:
# si las dependencias no cambian, Docker no las vuelve a descargar en cada build.
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Ahora copiamos el resto del codigo fuente y compilamos el .jar final.
COPY src ./src
RUN mvn clean package -DskipTests

# --- ETAPA 2: ejecucion ---
# Usamos una imagen mas liviana, con solo el JRE (no necesitamos el JDK para ejecutar).
FROM eclipse-temurin:25-jre-jammy

WORKDIR /app

# Copiamos unicamente el .jar generado en la etapa de build, descartando
# todo lo demas (codigo fuente, Maven, cache de dependencias, etc).
COPY --from=build /build/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]