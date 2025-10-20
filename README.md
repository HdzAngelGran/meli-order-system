# Meli Order System

## Summary

This repository contains the Meli Order System — a Spring Boot 3.x + Java 17 web service that implements the Order creation API for an online store. Focuses on implementing a RESTful resource for creating, reading, updating and deleting orders (CRUD) backed by a relational database (H2 for development, PostgreSQL for production). The project follows standard layered architecture: controllers, services, repositories and domain models.

Intended audience: new developers joining the project, reviewers, and other technical stakeholders.

Table of contents

- Project Title and Description
- Getting Started (Prerequisites, Installation)
- Running the Application (startup script)
- API Documentation (endpoints, Postman)
- Database Configuration (dev/prod + properties)
- Code Documentation & Standards
- Team Decisions and Justifications
- C2 Checklist: Missing items and how to address them
- How to contribute

## Project Title and Description

Project: Meli Order System — Order Creation Service

Purpose: Provide a reliable, testable REST API to create and manage orders. This service is designed to be consumable by frontend applications and other backend services.

## Getting started

Prerequisites

- Java 17 (JDK)
- Gradle wrapper (included) or Gradle 8+
- Git
- (Optional) Docker and Docker Compose for production-like runs

Install

Clone the repository and build:

```powershell
git clone <REPO_URL>
cd "meli"
.\gradlew.bat clean build
```

## Running the application

Development (H2, Swagger enabled):

```powershell
./scripts/start-dev.ps1
```

QA profile (PostgreSQL, Swagger enabled):

On Linux/macOS:

```bash
./scripts/start-qa.sh
```

On Windows:

```powershell
./scripts/start-qa.bat
```

Or run directly with Gradle:

```powershell
.\gradlew.bat bootRun --args="--spring.profiles.active=qa"
```

Alternatively, run directly with Gradle:

```powershell
.\gradlew.bat bootRun --args="--spring.profiles.active=dev"
```

Production (example): build jar and run with `prod` profile (requires Postgres env vars):

```powershell
.\gradlew.bat clean build
java -jar build\libs\*SNAPSHOT*.jar --spring.profiles.active=prod
```

Environment variables for production (example):

- DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PASSWORD

## API Documentation

The API exposes endpoints under `/api/orders`:

- POST /api/orders — create an order
- GET /api/orders/{id} — retrieve order by id
- PUT /api/orders/{id} — update order (partial updates limited)
- DELETE /api/orders/{id} — delete order

Postman collection: `docs/postman_collection.json` (import into Postman and set `{{baseUrl}}` to `http://localhost:8080`).

For visual reference: [Postman screenshot](./docs/postman_screenshots.md)

Swagger / OpenAPI:

- Swagger UI is available in `dev` profile at `/swagger-ui/index.html` (springdoc). See the C2 enhancements section for details.

## Database configuration

Development (H2): default configuration is in `src/main/resources/application-dev.properties` and the base `application.properties` contains defaults pointing to H2.

Production (PostgreSQL): configure `src/main/resources/application-prod.properties` and set environment variables for DB connection. The `prod` profile disables H2 console and Swagger by default.

Example `application-prod.properties` variables are already included as templates in the project. Use environment variables to inject secrets.

## Code documentation & standards

- JavaDoc: public classes and service methods should include JavaDoc comments.
- Inline comments for non-obvious logic.
- Use consistent formatting and follow the project's style (4-space indentation, braces on same line).

## Tests

Run unit and integration tests using Gradle:

```powershell
.\gradlew.bat test
```

## Team decisions and justifications

This section documents high-level choices made:

- Spring Boot 3.x & Java 17: modern LTS Java with Spring Boot productivity features and security fixes.
- Gradle wrapper: reproducible builds across developer machines and CI.
- Layered architecture (Controller -> Service -> Repository): clear separation of concerns and easy testing.
- H2 for development: fast, in-memory DB for rapid iteration. Postgres for production: proven, feature-rich RDBMS.
