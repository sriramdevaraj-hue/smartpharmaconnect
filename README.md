# SmartPharma Connect -- Deployment Guide

Event-Driven Pharmacy Supply Backend built with Spring Boot 3 featuring microservices, RabbitMQ, PostgreSQL, secured via JWT, and observed with Prometheus + Zipkin.

## �� Prerequisites

Before deploying SmartPharma Connect, ensure you have the following installed:

- **Java 17+** - Required for Spring Boot 3+
- **Maven 3.8+** - For building microservices
- **Docker 20.10+** - For containerization
- **Docker Compose 2.0+** - For orchestration
- **Git** - For version control

### Verify Prerequisites

```bash
java -version
mvn -version
docker --version
docker compose version
```
## �� Step-by-Step Deployment

### Step 1 — Build All Microservices

Build all microservices from the project root:

```bash
mvn -q -DskipTests package
```

### Step 2 — Start Infrastructure Services

Start the core infrastructure services that support the microservices through terminal:

```bash
cd Downloads/smarpharmaconnect
podman-compose up postgres rabbitmq zipkin 
```

This will start:
- **PostgreSQL** (port 5432) - Multi-database setup
- **Gateway** (port 8080) - API gateway and routing
- **Zipkin** (port 9411) - Distributed tracing

### Step 3 — Run Database Migrations

Execute Flyway migrations for each microservice to set up database schemas:

```bash
# Auth Service
cd auth-service
mvn flyway:migrate -Plocal 

# Inventory Service (when available)
cd ../inventory-service
mvn flyway:migrate -Plocal 

# Order Service (when available)
cd ../order-service
mvn flyway:migrate -Plocal 

# Pricing Service (when available)
cd ../pricing-service
mvn flyway:migrate -Plocal 

# Notification Service (when available)
cd ../notification-service
mvn flyway:migrate -Plocal 

# Return to project root
cd ..
```

### Step 5 — Start Business Microservices

Deploy the business logic microservices:

# Start services individually
mvn spring-boot:run -pl auth-service
mvn spring-boot:run -pl inventory-service
mvn spring-boot:run -pl pricing-service
mvn spring-boot:run -pl order-service
mvn spring-boot:run -pl notification-service
```

This will start:
- **Auth Service** (port 8082) - User authentication and JWT management
- **Order Service** (port 8084) - Order lifecycle management
- **Pricing Service** (port 8083) - Pricing calculations
- **Inventory Service** (port 8086) - Medicine catalog and stock
- **Notification Service** (port 8085) - Email/SMS notifications

### Step 6 — Verify Running Services

Check that all services are running properly:

```bash
# List all running containers
docker ps

# Check service health
docker compose ps

# View logs for specific services
docker logs smartpharmaconnect-auth-service
docker logs smartpharmaconnect-gateway
docker logs smartpharmaconnect-registry
```

### Step 7 — Cleanup Instructions

When you're done with development, clean up resources:

```bash
# Stop all services (keeps volumes)
docker compose down

# Stop all services and remove volumes (complete cleanup)
docker compose down -v

# Remove all images (optional)
docker compose down --rmi all
```

## �� Notes & Tips

### �� Restarting Services

To restart a specific service:
```bash
docker compose restart auth-service
```

To restart all services:
```bash
docker compose restart
```

### �� Development Tips

- Use `mvn spring-boot:run` for individual services during development
- **Database Access**: Connect to PostgreSQL at `localhost:5432` with credentials `postgres/password`
- **Service Registry**: View registered services at `http://localhost:8761`
- **API Gateway**: All API requests go through `http://localhost:9000`

### �� Monitoring & Observability

- **Tracing**: Zipkin at `http://localhost:9411`
- **Health Checks**: Each service exposes health endpoints at `/actuator/health`

---

👨‍💻 **Maintainer**: SmartPharmaConnect
