# E-commerce Application

## Table of Contents
- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [Entity-Relationship Model](#entity-relationship-model)
- [Architecture](#architecture)

## Overview
This is an E-commerce backend application that allows customers to browse products, purchase products and send
email notifications for order confirmation and payment confirmation. 
The application is built with a microservices architecture, integrating various services like customer management, 
product inventory, payment processing, and notifications.

## Technologies Used
- Java 17
- Spring Boot
- Spring Cloud (Feign, Gateway, Config)
- Keycloak integration (for authentication and authorization)
- Kafka (for message-driven communication)
- Feign (for inter-service communication)
- PostgreSQL
- MongoDB
- Docker (for containerization)

## Entity-Relationship Model
In this e-commerce platform, the Domain-Driven Design (DDD) pattern is used to manage the complexity of the business 
domain. Each microservice owns its domain logic and its corresponding database, whether it's MongoDB or PostgreSQL.
An Entity-Relationship Model (ERD) visual representation of the data entities and their relationships, which are 
segregated by service, can be found below:

![ERD with DDD](./diagrams/ERM%20with%20DDD.png)

## Architecture
This e-commerce platform is designed using microservices architecture to enable scalability, flexibility, and 
maintainability. Each service is loosely coupled and communicates through REST or messaging (Kafka). Below is a 
high-level overview of the services:

1. **API Gateway**: Handles all incoming traffic and routes it to the appropriate service.
2. **Config Server**: Centralized configuration management for all microservices, ensuring consistency across environments.
3. **Discovery Server**: Provides service discovery functionality, allowing microservices to register and discover other
services without hard-coded URLs.
4. **Order Service**: Manages customer orders checking stock availability and order confirmation.
5. **Customer Service**: Manages customer profiles.
6. **Product Service**: Manages product catalog, stock, and product-related information.
7. **Payment Service**: Manages payment processing and payment confirmation.
8. **Notification Service**: Sends notifications to customers for order and payment confirmations via Kafka.
9. **Zipkin**: Distributed Tracing with Zipkin.

![E-commerce Architecture](./diagrams/Ecommerce%20microservices%20architecture.png)