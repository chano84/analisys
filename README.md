# 📋 Spring Boot To-Do List API

Una aplicación RESTful construida con Spring Boot que permite crear informacion CSV, y obtener el summary de esta informacion.

## 🛠️ Tecnologías utilizadas

- Java 17
- Spring Boot 3.5.3
- Spring Web
- Spring Data JPA
- H2 Database 
- Maven

 
# Compilar y ejecutar
./mvnw spring-boot:run

# Endpoints principales

| Método | Endpoint                      | Descripción                              |
|--------|-------------------------------|------------------------------------------|
| POST   | /scraped-data/upload          | Upload file csv                          |
| GET    | /analysis/summary             | Resumen de productos                     |
| GET    | /analysis/top-products        | Productos mas costoso por categoria      |
| GET    | /analysis/trend/{productName} | Precio promedio de productos disponibles |
 
