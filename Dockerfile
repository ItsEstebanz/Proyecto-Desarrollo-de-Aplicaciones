FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY pom.xml .
RUN mvn --batch-mode --no-transfer-progress dependency:go-offline

COPY src ./src

RUN mvn --batch-mode --no-transfer-progress \
    -DskipTests \
    clean package


FROM eclipse-temurin:21-jre-jammy AS runtime

RUN apt-get update \
    && apt-get install --yes --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/* \
    && groupadd --system spring \
    && useradd --system --gid spring spring

WORKDIR /app

COPY --from=build \
    --chown=spring:spring \
    /workspace/target/*.jar \
    /app/application.jar

USER spring:spring

EXPOSE 8081

HEALTHCHECK \
    --interval=10s \
    --timeout=3s \
    --start-period=30s \
    --retries=6 \
    CMD curl --fail --silent http://localhost:8081/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "/app/application.jar"]