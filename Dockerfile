# Primera etapa: Compilación con Maven
FROM maven:3.8.6-openjdk-17 as builder

WORKDIR /workspace/app

# Copiar el archivo POM y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente
COPY src src

# Compilar el proyecto y empaquetarlo
RUN mvn package -DskipTests

# Segunda etapa: Ejecución con Amazon Corretto
FROM amazoncorretto:17-alpine

WORKDIR /app

# Copiar el JAR desde la etapa de compilación
COPY --from=builder /workspace/app/target/*.jar app.jar

# Exponer puerto y ejecutar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]