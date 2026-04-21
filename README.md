# 📋 Spring Boot API - Dataart 

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

# Demo visual

Para generar el video de demostracion que simula abrir un mail y visualizar el reporte:

```bash
./scripts/render-email-demo.sh
```

El archivo final se guarda en:

```bash
media/email-demo/output/email-demo.mp4
```

# Endpoints principales

| Método | Endpoint                      | Descripción                              |
|--------|-------------------------------|------------------------------------------|
| POST   | /scraped-data/upload          | Upload file csv                          |
| GET    | /analysis/summary             | Resumen de productos                     |
| GET    | /analysis/top-products        | Productos mas costoso por categoria      |
| GET    | /analysis/trend/{productName} | Precio promedio de productos disponibles |
 
