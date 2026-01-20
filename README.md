# Employee Management System

A Spring Boot-based Employee Management System that provides full **CRUD RESTful APIs**, **unit tests**, **validation**, and supports **pagination & sorting**.

---

## Features

- **CRUD Operations**  
  - Create, Read, Update, Delete employees via REST APIs.
- **Validation**  
  - Input validation using Jakarta Validation (`@NotBlank`, `@Email`, etc.).
- **Pagination & Sorting**  
  - Retrieve paginated and sorted lists of employees.
- **Unit Testing**  
  - Service layer fully tested with **Mockito**.
- **Mapper Layer**  
  - DTOs mapped from entities using a clean mapper approach.
- **RESTful API**  
  - Follows best practices for HTTP status codes and endpoints.

---

## Technology Stack

- Java 17  
- Spring Boot 3.x  
- Spring Data JPA  
- Hibernate  
- H2 / MySQL (configurable)  
- Jakarta Bean Validation  
- Mockito & JUnit 5 for unit testing  
- Lombok for boilerplate reduction

---

## Getting Started

### Clone the repository

```bash
git clone https://github.com/your-username/employee-management-system.git
cd employee-management-system
