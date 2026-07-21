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

```
{
  "username": "cliente1",
  "email": "cliente1@tienda.com",
  "password": "123456"
}
```

**Resultado obtenido**

- HTTP 200 OK
- Usuario almacenado en la base de datos

---

### 4. Registrar un usuario ADMIN

**Endpoint**

POST http://localhost:8080/auth/register

**Body**

 RAW JSON
 
```
{
    "username": "admin",
    "password": "Admin123",
    "email": "admin@tienda.com"
}
```

**Resultado obtenido**

- HTTP 200 OK
- Usuario almacenado en la base de datos


**Adiccional**

- Entrar en la app de la BBDD y cambiar el ROL predefinido de CLIENTE a ADMIN del usuario "admin". Para asegurar que solo el administrador de la BBDD decide que usuario es ADMIN.

---

### 5. Login CLIENTE

**Endpoint**

POST http://localhost:8080/auth/login

**Body**

 RAW JSON
 
```
{
   "username": "cliente1",
   "password": "123456"
}
```

**Resultado obtenido**

- HTTP 200 OK
- Login del cliente.

---

### 6. Login ADMIN

**Endpoint**

POST http://localhost:8080/auth/login

**Body**

```
 RAW JSON

{
    "username": "admin",
    "password": "Admin123"
}
```

**Resultado obtenido**

- HTTP 200 OK
- Login del administrador.
- Obtendremos un token, que utilizaremos para todas las peticiones de administrador.

---

### 7. Guardar automáticamente el JWT Environments

Es necesario crearlo para que quede guardado el Token.

Icono “+”> Environments.
Nombre: API-Ecommerce

Variable: Token
Value: (Vacío)

En POSTMAN arriba a la derecha seleccionamos el nombre del Environments que hemos creado: API-Ecommerce2
Esto te ahorrará tener que copiar y pegar el token en cada petición.


**Endpoint**

POST http://localhost:8080/auth/login

Pestaña Authorization, tiene que estan en “No Auth”

**Body**

 RAW JSON

```
{
    "username": "admin",
    "password": "Admin123"
}
```

**Añadir el Script**

Ir a la pestaña Scripts> Post-response > Escribe:

```
const response = pm.response.json();

pm.environment.set("token", response.data.token);

console.log("TOKEN GUARDADO:");
console.log(response.data.token);
```


**Ejecutar Login Admin**

Pulsar “Send”. Te devuelve:

```
{
    "token":"eyJhbGciOiJIUzI1NiJ9...."
}

```

**Comprobar que se ha guardado el token**

Ir a Environments> API-Ecommerce> Token el la columna de Value aparecerá el token.

Vamos a generar una nueva petición.

**Endpoint**

GET http://localhost:8080/categorias


**body**
 RAW JSON
 
```
{
    "username":"admin",
    "password":"Admin123"
}
```

Pestaña Headers> añadimos: 
Key: “Authorization”
Value: `Bearer {{token}}`

Ya tendrá que permitir acceso.

**Resultado obtenido**

- HTTP 200 OK

---

### 8. CRUD de Categorías

#### 8.1 Crear una categoría

**Endpoint**

POST http://localhost:8080/categorias

**Headers**

Authorization   -   `Bearer {{token}}`

**Body**

 RAW JSON
 
```
{
    "nombre": "Informática"
}

```

**Resultado obtenido**

- HTTP 200 OK
- Devuelve la categoría creada.

---

#### 8.2 Listar categorías

**Endpoint**

GET http://localhost:8080/categorias

**Headers**

Authorization   -   `Bearer {{token}}`

**Resultado obtenido**

- HTTP 200 OK
- Devuelve la categoría creada.

---

#### 8.3 Actualizar una categoría

**Endpoint**

PUT http://localhost:8080/categorias/1

**Headers**

Authorization   -   `Bearer {{token}}`

**Body**

 RAW JSON
 
```
{
    "nombre": "Electrónica" 
}

```

**Resultado obtenido**

- HTTP 200 OK
- Categoría actualizada.

---

#### 8.4 Eliminar una categoría

**Endpoint**

DELETE http://localhost:8080/categorias/1

**Headers**

Authorization   -   `Bearer {{token}}`

**Resultado obtenido**

- HTTP 204 No Content
- Categoría eliminada y no devuelve valor..

---

#### 8.5 Necesario crear categorias

Es necesario crear al menos una categoría para realizar los siguientes test.
Se puede crear mediante POSTMAN, desde Eclipse ejecutando un data.sql o directamente desde la BBDD.

```
-- Insertar categorías solo si no existen
INSERT INTO categorias (id, nombre, descripcion)
SELECT 1, 'Informática', 'Ordenadores, portátiles y accesorios'
WHERE NOT EXISTS (SELECT 1 FROM categorias WHERE id = 1);

INSERT INTO categorias (id, nombre, descripcion)
SELECT 2, 'Telefonía', 'Móviles, tablets y accesorios'
WHERE NOT EXISTS (SELECT 1 FROM categorias WHERE id = 2);

INSERT INTO categorias (id, nombre, descripcion)
SELECT 3, 'Electrodomésticos', 'Electrodomésticos del hogar'
WHERE NOT EXISTS (SELECT 1 FROM categorias WHERE id = 3);

INSERT INTO categorias (id, nombre, descripcion)
SELECT 4, 'Televisión', 'Televisores y accesorios audiovisuales'
WHERE NOT EXISTS (SELECT 1 FROM categorias WHERE id = 4);

INSERT INTO categorias (id, nombre, descripcion)
SELECT 5, 'Videojuegos', 'Consolas, juegos y accesorios'
WHERE NOT EXISTS (SELECT 1 FROM categorias WHERE id = 5);
```


### 9. CRUD de Producto

#### 9.1 Crear una Producto

**Endpoint**

POST http://localhost:8080/productos

**Headers**

Authorization   -   `Bearer {{token}}`

**Body**

 RAW JSON
 
```
{
    "nombre": "Portátil Lenovo",
    "descripcion": "Lenovo ThinkPad E16",
    "precio": 899.99,
    "stock": 10,
    "categoriaId": 1
}
```

**Resultado obtenido**

- HTTP 200 OK
- Devuelve la producto creada.

---

#### 9.2 Listar productos

**Endpoint**

GET http://localhost:8080/productos


**Resultado obtenido**

- HTTP 200 OK
- Debe devolver una lista de productos.

---

#### 9.3 Obtener un producto

**Endpoint**

GET http://localhost:8080/productos/1


**Resultado obtenido**

- HTTP 200 OK
- Debe devuelve el producto 1.

---

#### 9.4 Paginación

**Endpoint**

GET http://localhost:8080/productos/paginado?page=0&size=5


**Resultado obtenido**

- HTTP 200 OK
- Debe devolver un ApiResponse<Page<ProductoResponse>>.

---