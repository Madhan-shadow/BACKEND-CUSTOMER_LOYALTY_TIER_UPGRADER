# 🚀 Customer Loyalty Tier Upgrader Backend

A robust Spring Boot REST API for managing customer loyalty programs with automated tier upgrades based on purchase history and customer visits. The application provides secure authentication using JWT, role-based authorization with Spring Security, and interactive API documentation using Swagger.

---

## 📌 Features

- 🔐 JWT Authentication & Authorization
- 👥 Customer Profile Management
- 🛒 Purchase Record Management
- 🏪 Visit Record Management
- ⭐ Loyalty Tier Upgrade Rules
- 📈 Automatic Tier Upgrade Engine
- 📜 Tier Upgrade History Tracking
- 🛡️ Spring Security
- 📄 Swagger/OpenAPI Documentation
- 💾 MySQL Database Integration
- ⚡ RESTful APIs
- 🧩 Layered Architecture (Controller → Service → Repository → Entity)

---

## 🛠️ Technologies Used

- Java 21
- Spring Boot 3
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Swagger (SpringDoc OpenAPI)

---

## 📂 Project Structure

```
Customer_Loyalty_Tier_Upgrader
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.Customer_Loyalty_Tier_Upgrader
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── entity
│   │   │       ├── exception
│   │   │       ├── repository
│   │   │       ├── security
│   │   │       ├── service
│   │   │       └── servlet
│   │   └── resources
│   └── test
├── pom.xml
└── README.md
```

---

## ✨ Modules

- Authentication
- Customer Profiles
- Purchase Records
- Visit Records
- Tier Upgrade Rules
- Tier Upgrade Engine
- Tier History

---

## 🔐 Authentication

The application uses JWT-based authentication.

### Public APIs

- Register
- Login
- Swagger UI

### Protected APIs

- Customer Management
- Purchase Records
- Visit Records
- Tier Rules
- Tier Upgrade Engine

---

## 📚 API Documentation

After running the application, open:

```
http://localhost:8080/swagger-ui/index.html
```

OpenAPI JSON:

```
http://localhost:8080/v3/api-docs
```

---

## ⚙️ Getting Started

### Clone the Repository

```bash
git clone https://github.com/Madhan-shadow/BACKEND-CUSTOMER_LOYALTY_TIER_UPGRADER.git
```

### Navigate to the Project

```bash
cd BACKEND-CUSTOMER_LOYALTY_TIER_UPGRADER
```

### Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/customer_loyalty
spring.datasource.username=root
spring.datasource.password=your_password
```

### Run the Application

Using Maven

```bash
mvn spring-boot:run
```

Or

```bash
mvn clean install
```

---

## 📋 REST API Modules

- Authentication
- Customer Profile
- Purchase Record
- Visit Record
- Tier Upgrade Rules
- Tier Upgrade Engine

---

## 📈 Future Enhancements

- Email Notifications
- Loyalty Reward Points
- Admin Dashboard
- Analytics Reports
- Docker Support
- CI/CD Pipeline
- Unit & Integration Tests

---

## 👨‍💻 Author

**Madhan R**

- GitHub: https://github.com/Madhan-shadow
- LinkedIn: https://www.linkedin.com/in/madhan-r-82092b371

---

## ⭐ If you found this project useful

Please consider giving it a **Star ⭐** on GitHub.

---

## 📄 License

This project is created for learning, portfolio, and educational purposes.
