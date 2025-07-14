# API Servicio Usuario

Este proyecto es una API RESTful para la gestión de usuarios, desarrollada con **Spring Boot** y **Java**. Incluye autenticación JWT, documentación OpenAPI/Swagger, y acceso a base de datos H2 embebida. 

## Tecnologías Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Security** (JWT)
- **Spring Data JPA**
- **H2 Database** (desarrollo)
- **Swagger/OpenAPI** (documentación)
- **Maven** (gestión de dependencias)

## Estructura del Proyecto

```
├── src/main/java/com/gestion/api/
│   ├── config/         # Configuración de seguridad y JWT
│   ├── controller/     # Controladores REST
│   ├── entity/         # Entidades JPA
│   ├── exception/      # Manejo de excepciones
│   ├── model/          # Modelos de datos
│   ├── repository/     # Repositorios JPA
│   └── service/        # Lógica de negocio
├── src/main/resources/ # Configuración y scripts SQL
├── pom.xml             # Dependencias Maven
```

## Endpoints Principales

### Autenticación
- `POST /authenticate` — Autentica usuario y retorna JWT.

### Usuarios
- `GET /usuarios` — Lista todos los usuarios.
- `GET /usuarios/{id}` — Obtiene usuario por ID.
- `POST /usuarios` — Crea un nuevo usuario.
- `PUT /usuarios/{id}` — Actualiza usuario existente.
- `DELETE /usuarios/{id}` — Elimina usuario por ID.

### Teléfonos
- `GET /usuarios/{id}/telefonos` — Lista teléfonos de un usuario.
- `POST /usuarios/{id}/telefonos` — Añade teléfono a usuario.

## Seguridad
- Autenticación basada en JWT.
- Endpoints protegidos requieren token en el header `Authorization: Bearer <token>`.

## Documentación Interactiva
- Accede a Swagger UI en: `http://localhost:8080/swagger-ui.html`

## Ejemplo de Uso

```bash
# Autenticación
curl -X POST http://localhost:8080/authenticate -H "Content-Type: application/json" -d '{"username":"admin","password":"admin"}'

# Obtener usuarios
curl -H "Authorization: Bearer <token>" http://localhost:8080/usuarios
```

## Configuración

- Edita `src/main/resources/application.properties` para configurar la base de datos y otros parámetros.

## Scripts SQL
- `schema.sql`: Estructura de la base de datos.
- `data.sql`: Datos iniciales.

## Ejecución

```bash
mvn spring-boot:run
```

## Pruebas

- Las pruebas unitarias se encuentran en `src/test/java/com/gestion/api/controller/UsuarioControllerTest.java`.

## Autor

- Desarrollado por David Vargas

---

> ![Spring Boot Logo](https://spring.io/images/spring-logo-2019-627d8b3b7b7c7c2b2c7b7b7b7b7b7b7b.svg)
> 
> **API moderna, segura y documentada.**
