# Test Manuales

## Objetivo

Verificar el correcto funcionamiento de los endpoints de la API antes de implementar los tests automatizados.

## Herramientas

- Postman
- MySQL
- Swagger

## Casos de prueba

### 1. Arrancar la aplicación

Tomcat started on port(s): 8080

Started ApiGestionProductosPedidosApplication

### 2. Comprobar que la API responde

GET http://localhost:8080/productos

**Resultado esperado**

- HTTP 200 OK

**Resultado obtenido**

✔ Correcto

---





///////////////////////// EJEMPLO
### 1. Crear un usuario

**Endpoint**

POST /api/users

**Body**

{
    "name":"Juan",
    "email":"juan@test.com"
}

**Resultado esperado**

- HTTP 201 Created
- Usuario almacenado en la base de datos

**Resultado obtenido**

✔ Correcto

---




