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
- `POST /usuarios` — Crea un nuevo usuario.

### Teléfonos
- `GET /usuarios/{id}/telefonos` — Lista teléfonos de un usuario.
- `POST /usuarios/{id}/telefonos` — Añade teléfono a usuario.

## Seguridad
- Autenticación basada en JWT.
- Endpoints protegidos requieren token en el header `Authorization: Bearer <token>`.

## Documentación Interactiva
- Accede a Swagger UI en: `http://localhost:8080/swagger`

## Instalación 

### Prerequisites
- Java Development Kit (JDK) 17 or later
- Maven
- Postman (for testing the API)

### 1. Clone the Repository

```
git clone https://github.com/davidalfonsovq/gestion.git
```

## Scripts SQL 
- Se encuentran en el directorio src\main\resources
- `schema.sql`: Estructura de la base de datos definición de Tablas Usuario y Telefono.
- `data.sql`: Datos iniciales (se agregó un solo usuario para poder probar inicialmente el getUsers.

## Ejecución

```bash
mvn spring-boot:run

## Ejemplo de Uso

```bash
# Autenticación
- Primero debe obtenerse un token válido el cual se seteará en los headers para utilizar createUser y getUsers
curl -X POST http://localhost:8080/authenticate -H "Content-Type: application/json" -d '{"username":"admin","password":"1234"}'

# Obtener Usuarios
- Metodo GET
- Path: 'http://localhost:8080/api/getUsers'
- Una vez obtenido el Token a través del authenticate, se debe copiar al header de Authorization con el prefijo "Bearer " como se explica a continuación:

curl --location 'http://localhost:8080/api/getUsers' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer  <token>'

#Crear Usuarios
- Metodo POST
- Path:
- Una vez obtenido el Token a través del authenticate, se debe copiar al header de Authorization como se explicó en el Obtener Usuarios.
- Para el body se deberá respetar la siguiente estructura:

{
  "name": "Fernando",
  "email": "Fernandoarias@gmail.com",
  "password": "Clavedefernando3",
  "phones": [
    {
      "number": "98524466",
      "citycode": "2",
      "contrycode": "56"
    }
  ]
}

- Se encuentra validado el formato del correo.
- Se encuentra validado el password con una expresión regular configurable mínimo largo 8 caracteres, al menos un número, al menos una mayúscula.
- El curl queda de la siguiente manera:

curl --location 'http://localhost:8080/api/createUser' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <token>' \
--data-raw '
{
  "name": "Fernando",
  "email": "Fernandoarias@gmail.com",
  "password": "Clavedefernando3",
  "phones": [
    {
      "number": "98524466",
      "citycode": "2",
      "contrycode": "56"
    }
  ]
}'

## Configuración

- Edita `src/main/resources/application.properties` para configurar la base de datos y otros parámetros.
  como por ejemplo configuración de exp del password; se encuentra en application.properties : regex.password.


## Pruebas

- Las pruebas unitarias se encuentran en `src/test/java/com/gestion/api/controller/UsuarioControllerTest.java`.

## Swagger

- Iniciado el proyecto, desde el navegador se podrá acceder al swagger desde la siguiente URL:
URL: http://localhost:8080/swagger

Se debe ejecutar el jwt-authentication-controller para generar token, seleccionando el boton "Try it out" y nuevamente completando los parametros
username, password; como se indica en String 

username : admin
password : 1234 

Posteriormente este Token debe ser copiado al inicio en el Autorización (boton "Authorizate") 
obs: aqui no es necesario ya colocar el prefijo "Bearer ", unicamente copiar el Token generado y autorizar.

Una vez autorizado ya se pueden consumir los servicios de listar usuarios y crear usuarios.

## Autor

- Desarrollado por David Vargas Q.

---

> ![Spring Boot Logo](https://spring.io/images/spring-logo-2019-627d8b3b7b7c7c2b2c7b7b7b7b7b7b7b.svg)
> 
> **API moderna, segura y documentada.**
