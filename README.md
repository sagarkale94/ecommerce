# Sample E-commerce REST APIs using Spring Boot 3, Spring Security 6, and JWT

This is a simple mock project demonstrating RESTful APIs for an e-commerce system. The project is built using **Spring Boot 3**, **Spring Security 6**, and **JWT (JSON Web Tokens)** for authentication and authorization.

The project provides a set of basic APIs that can be used for e-commerce functionalities, including user authentication, product management, and order processing.

## Features

- **JWT Authentication**: Secure API access with JWT tokens.
- **Spring Security**: Role-based authentication and authorization.
- **Swagger Integration**: API documentation using Swagger UI.
- **RESTful Endpoints**: Simple and clean REST APIs for various e-commerce functionalities.

## Technologies Used

- **Spring Boot 3**: The main framework used for building the APIs.
- **Spring Security 6**: For securing the application with authentication and authorization.
- **JWT**: JSON Web Token for secure API access.
- **Swagger**: API documentation and testing.
- **H2 Database**: In-memory database for testing purposes (you can switch to any other database as per requirement).

## API Endpoints

Here are the sample endpoints included in the project:

- `POST /api/auth/login`: Authenticate user and get a JWT token.
- `GET /api/products`: Fetch a list of all products (requires authentication).
- `POST /api/products`: Add a new product (requires Admin role).
- `GET /api/orders`: Fetch orders (requires authentication).
- `POST /api/orders`: Place an order (requires authentication).

## Swagger Documentation
![screencapture-localhost-8080-swagger-ui-index-html-2025-04-10-11_50_45](https://github.com/user-attachments/assets/aa2cc261-0ac5-4c75-b193-bcf829f6fb4c)


## How to Run the Project

```bash
git clone https://github.com/sagarkale94/ecommerce.git
cd ecommerce
mvn spring-boot:run
```

