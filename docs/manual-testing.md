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

---

### 2. Comprobar que la API responde

**Endpoint**

GET http://localhost:8080/productos

**Resultado esperado**

- HTTP 200 OK

**Resultado obtenido**

✔ Correcto

---

### 3. Registrar un usuario CLIENTE

**Endpoint**

POST http://localhost:8080/auth/register

**Body**

 RAW JSON

{
  "username": "cliente1",
  "email": "cliente1@tienda.com",
  "password": "123456"
}

**Resultado obtenido**

- HTTP 200 OK
- Usuario almacenado en la base de datos

---

### 4. Registrar un usuario ADMIN

**Endpoint**

POST http://localhost:8080/auth/register

**Body**

 RAW JSON

{
    "username": "admin",
    "password": "Admin123",
    "email": "admin@tienda.com"
}

**Resultado obtenido**

- HTTP 200 OK
- Usuario almacenado en la base de datos


**Adiccional**

- Entrar en la app de la BBDD y cambiar el ROL predefinido de CLIENTE a ADMIN del usuario "admin". Para asegurar que solo el administrador de la BBDD decide que usuario es ADMIN.

---





