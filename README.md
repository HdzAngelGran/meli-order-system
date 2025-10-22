# Order Management System API for MELI E-Commerce

## Project Description

This project is a robust, enterprise-grade Order Management System (OMS) built using Java and the Spring Boot framework. Its core purpose is to provide a scalable and resilient backend solution for processing, tracking, and managing customer orders. The application leverages the full power of the Spring ecosystem to deliver a flexible, decoupled, and maintainable architecture, designed to solve complex order lifecycle challenges in a modern e-commerce or retail environment.

The system is engineered not just as a simple CRUD application, but as a strategic asset capable of integrating into larger enterprise workflows. It emphasizes clean architecture, advanced configuration management, and comprehensive API documentation to ensure high degrees of autonomy, adaptability.

## Key Features and Functionality

- **Full Order Lifecycle Management**: Complete CRUD (Create, Read, Update, Delete) operations for orders.
- **Dynamic Order Status Tracking**: Allows for creating and assigning various statuses to orders (e.g., `CREATED`, `CANCELED`, `SHIPPED`, `DELIVERED`).
- **Product and User Integration**: Foundational entities for `Product` and `User` to simulate a real-world e-commerce backend.
- **Advanced Data Auditing**: Utilizes Hibernate Envers to automatically track and version all changes to critical entities like `Order`, providing a complete audit trail.
- **Centralized Exception Handling**: A global exception handler that provides clear, consistent, and user-friendly error responses for all API endpoints.
- **Time-Based UUID Generation**: Employs `uuid-creator` to generate time-sortable UUIDs (v7), a strategic choice for creating scalable and performant primary keys that prevent database index fragmentation.

### Spring and Spring Boot Frameworks

The application is built on **Spring Boot 3**, which significantly simplifies development by providing sensible defaults and auto-configuration. The choice of specific Spring modules was deliberate:

- **Spring Web**: Provides the foundation for building the RESTful API, including the embedded Tomcat server for easy deployment.
- **Spring Data JPA**: Simplifies the data access layer by removing boilerplate JDBC code. It allows the application to interact seamlessly with the database through repository interfaces. The implementation demonstrates a deep understanding of ORM, including entity relationships and query optimization.
- **Spring Boot Actuator**: Integrated to expose production-ready endpoints for monitoring application health, metrics, and other internal states. This is critical for maintaining a reliable system in a production environment and showcases an advanced understanding of operational concerns.
- **Hibernate Envers**: This choice demonstrates a proactive approach to data governance and auditing. By integrating Envers, the system automatically captures a complete history of data modifications, a feature often required for compliance and advanced business intelligence.

### Profiles and Environments: A Strategy for Adaptability

The project exhibits a sophisticated strategy for managing configurations across multiple environments (`dev`, `qa`, `prod`), showcasing autonomy and adaptability.

- **Layered Configuration**: The system uses a combination of `application.properties` for shared settings and profile-specific files (`application-dev.properties`, `application-qa.properties`, `application-prod.properties`) for environment-specific overrides.
- **Externalized Secrets**: Sensitive data, such as database credentials, is managed through environment variables (`.env` files), which are loaded at runtime. This is a security best practice that prevents secrets from being committed to version control.
- **Startup Scripts**: The inclusion of environment-specific startup scripts (`start-dev.ps1`, `start-qa.bat`) by automating the setup process for different stages of the development lifecycle, reducing manual errors and improving developer efficiency.

### API Design (Swagger/OpenAPI)

API documentation and testing are handled and a focus on digital tool promotion, using **Springdoc OpenAPI**.

- **Automated Documentation**: The API is self-documenting. By integrating `springdoc-openapi-starter-webmvc-ui`, the project automatically generates a comprehensive Swagger UI portal from the controller mappings and annotations.
- **Interactive Testing**: The Swagger UI allows developers and QA teams to interactively test every endpoint directly from their browser, complete with parameter descriptions, request body schemas, and response examples. This promotes a culture of quality and accelerates the testing cycle.
- **Innovative Implementation**: This setup goes beyond basic documentation by providing a "single source of truth" for the API contract, which can be used to generate client SDKs and automate contract testing, demonstrating a forward-thinking approach to API lifecycle management.

### Database Schema

The application uses a relational database model mapped via JPA entities. Key entities include `Order`, `OrderItem`, `Product`, `Status`, and `User`. The schema is designed for scalability, with proper indexing and relationships defined to ensure data integrity and query performance.

## Innovation Strategy and Impact

### Innovative Elements (Team decisions)

1.  **Audit-as-a-Service Model**: The integration of Hibernate Envers is positioned as an internal "audit-as-a-service." This innovative approach provides a transparent, no-code solution for tracking data lineage, which has wide applicability in regulated industries (finance, healthcare) and for advanced analytics (understanding user behavior over time). Its scalability is high, as it leverages the database's native capabilities, and its economic feasibility is excellent due to its inclusion in the open-source Hibernate library.
2.  **Time-Sorted UUIDs for Scalability**: The choice of time-based UUIDs (v7) is a subtle but powerful innovation. Unlike random UUIDs, they are chronologically sortable and index-friendly, which is critical for high-throughput systems. This addresses a common scalability bottleneck in distributed databases, demonstrating a deep understanding of system design. The social impact is indirect but significant: it enables the creation of more reliable and performant systems that users depend on.
3.  **Use of `BaseEntity` and `ExposedEntity`**: This inheritance model promotes DRY (Don't Repeat Yourself) principles. Common fields like a numeric `id` and timestamps are centralized in `BaseEntity`, while `ExposedEntity` provides a `UUID` for public exposure. This simplifies the creation of new data models and ensures consistency Also it provides a critical security benefit by decoupling internal database identifiers (sequential numeric IDs) from public-facing ones (UUIDs). Exposing only the UUID prevents enumeration attacks, where a malicious user could guess sequential IDs to unlawfully access or scrape data.
4.  **Dedicated Endpoint for Status Updates**: The `OrderController` separates the logic for general order updates from status-specific update this garantice that every status change can be specifically logged and validated, providing a clear and reliable history of an order as it moves through the fulfillment process. This is crucial for operational tracking, reporting, and customer service inquiries.
5.  **Use of Data Transfer Objects (DTOs)**: DTOs create a stable contract for the API that is independent of the internal database model. This means the underlying database tables can be refactored or optimized without breaking the API for frontend clients or external consumers. It provides a clear separation of concerns also it prevents accidental data leakage. By explicitly mapping only the necessary fields from the internal entity to the response DTO, sensitive information (like user password hashes, internal notes, or profit margins) is never exposed through the API.

### Impact Evaluation

This project is designed to serve as a reference model for building modern, enterprise-ready Spring Boot applications.

- **For Stakeholders**: It provides a reliable, auditable, and scalable system that reduces business risk and provides a solid foundation for future growth.
- **For Developers**: It serves as a best-practice template that promotes clean code, robust testing, and efficient workflows, accelerating digital transformation within the organization.
- **Ethical Considerations**: By building in a strong auditing mechanism from the start, the project demonstrates a commitment to data transparency and accountability, which are key ethical principles in modern technology.

## Documentation and Testing

### Source Code Documentation

The source code is documented using standard JavaDoc conventions, with a focus on explaining the _why_ behind complex logic, not just the _what_. This approach, combined with the self-documenting nature of the code (clear naming, small methods), ensures that other developers can autonomously understand and contribute to the project.

### Testing Strategy

The project employs a multi-layered testing strategy that demonstrates an advanced skill in managing integrations and promoting quality.

- **Unit Tests**: JUnit 5 is used to test individual components in isolation (e.g., `OrderServiceImplTest`). This ensures that core business logic is correct.
- **Integration Tests**: The `@SpringBootTest` annotation is used to write integration tests (`OrderControllerIntegrationTest`) that validate the interactions between different layers of the application, from the controller down to the database. This ensures that the system works as a cohesive whole.
- **Digital Tool Promotion**: The testing process is designed to be run automatically as part of a CI/CD pipeline, promoting the use of digital tools to ensure that no code is merged without passing all quality checks.

## Endpoints

### Order Endpoints

| Method | Path                 | Description              |
| ------ | -------------------- | ------------------------ |
| POST   | /order               | Create a new order       |
| PUT    | /order/{uuid}        | Update an existing order |
| GET    | /order/{uuid}        | Get an order by UUID     |
| DELETE | /order/{uuid}        | Delete an order by UUID  |
| GET    | /order               | Get all orders           |
| PUT    | /order/{uuid}/status | Update the order status  |

### Status Endpoints

| Method | Path         | Description         |
| ------ | ------------ | ------------------- |
| GET    | /status      | Get all statuses    |
| POST   | /status      | Create a new status |
| PUT    | /status/{id} | Update a status     |
| DELETE | /status/{id} | Delete a status     |

### Postman Collection & Startup Script

To ensure effective outcomes, the project includes:

- **A comprehensive Postman collection** (`docs/postman_collection.json`) that allows for immediate, in-depth testing of the API. This was developed to cover all use cases and edge cases.
- [Postman testing screenshots](./docs/postman_screenshots.md)

## Getting Started / Setup Instructions

### Prerequisites

- **Java 17** or higher
- **Gradle 8.x**
- An IDE like IntelliJ IDEA or VS Code

### 1. Clone the Repository

```bash
git clone <your-repository-url>
cd meli
```

### 2. Configure the Environment

The application uses profile-specific property files. To run in a specific environment, you need to activate the corresponding Spring profile.

**For Development (using H2 in-memory database):**

1.  Create a `.env.dev` file in the root directory.
2.  Simply run the application.
3.  A H2 Console will start on `http://localhost:8080/h2-console`.

    ```
    H2DB_DB=mem:dev_db
    H2DB_USER=your_db_user
    H2DB_PASSWORD=your_db_password
    ```

**For QA/Production (using PostgreSQL):**

1.  Create a `.env` or `.env.qa` file in the root directory from the `.env.dev` template.
2.  For the `.env.qa` just add 'QA\_' before each environment variable.
3.  Update the `.env` file with your PostgreSQL database credentials:

    ```
    DB_HOST=localhost
    DB_PORT=5432
    DB_NAME=your_db_name
    DB_USER=your_db_user
    DB_PASSWORD=your_db_password
    ```

### 3. Run the Application

You can run the application using the provided Gradle wrapper or your IDE.

**Using Gradle:**

- **For Dev:**
  ```bash
  ./gradlew bootRun
  ```
- **For QA:**
  Activate the `qa` profile. You can do this by setting an environment variable or a system property.

  ```bash
  # In PowerShell
  $env:SPRING_PROFILES_ACTIVE="qa"
  ./gradlew bootRun

  # In Bash
  export SPRING_PROFILES_ACTIVE=qa
  ./gradlew bootRun
  ```

The application will start on `http://localhost:8080`.

## Economic Feasibility Analysis & Project Quote

For the full details click here: [economic_feasibility.md](./docs/economic_feasibility.md)

## Usage Examples

Once the application is running, you can access the interactive Swagger UI here:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Alternatively, you can use the provided Postman collection (`docs/postman_collection.json`) to send requests.

**Example: Create a new order using `curl`**

```bash
curl -X POST "http://localhost:8080/api/v1/orders" -H "Content-Type: application/json" -d \
'{
  "userId": "user-uuid-here",
  "products": [
    {
      "productId": "product-uuid-here",
      "quantity": 2
    }
  ]
}'
```
