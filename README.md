# 🛒 API E-commerce Pro Avanzado con Testing

API REST desarrollada con **Spring Boot** para la gestión de una tienda online, implementando autenticación mediante **JWT**, control de acceso por roles (**ADMIN / CLIENTE**), gestión de productos, categorías y pedidos, junto con validaciones automáticas, manejo global de excepciones, respuestas estandarizadas y una completa capa de **testing con JUnit, Mockito y MockMvc**.

---

# 🚀 Tecnologías Utilizadas

- ☕ Java 21
- 🌱 Spring Boot
- 🔐 Spring Security
- 🔑 JWT (JSON Web Token)
- 🗄️ Spring Data JPA
- 🐬 MySQL
- 🔄 Hibernate
- ✅ Jakarta Validation (@Valid)
- 📦 Maven
- 📬 Postman
- 🧪 JUnit 5
- 🎭 Mockito
- 🌐 MockMvc
- 📝 SLF4J Logging
- 💻 Eclipse IDE

---

# 📌 ¿Para Qué Sirve Esta API?

✔ Registro y autenticación de usuarios.

✔ Gestión de productos y categorías.

✔ Creación de pedidos.

✔ Control automático del stock.

✔ Cálculo automático del importe total del pedido.

✔ Protección mediante JWT y control de roles.

✔ Validación automática de peticiones.

✔ Manejo global de errores.

✔ Respuestas JSON estandarizadas.

✔ Paginación de resultados.

✔ Filtros dinámicos.

✔ Testing unitario y de controladores.

---

# 🏗️ Arquitectura del Proyecto

```text
src
└── main
    └── java
        └── com.tiendaonline.gestion
            ├── 📁 controller
            ├── 📁 dto
            │    ├── 📁 auth
            │    ├── 📁 producto
            │    ├── 📁 pedido
            │    └── 📁 common
            ├── 📁 exception
            ├── 📁 model
            ├── 📁 repository
            ├── 📁 security
            │    ├── 📁 config
            │    └── 📁 jwt
            ├── 📁 service
            └── 📁 service.impl

└── test
    └── java
        └── com.tiendaonline.gestion
            ├── 📁 controller
            │    ├── ProductoControllerTest
            │    └── PedidoControllerTest
            └── 📁 service
                 ├── ProductoServiceImplTest
                 └── PedidoServiceImplTest
```

---

# 📡 Endpoints Disponibles

## 🔐 Autenticación

| Método | Endpoint | Descripción |
|---------|----------|-------------|
| POST | `/auth/register` | Registrar usuario |
| POST | `/auth/login` | Iniciar sesión |

---

## 📂 Categorías

| Método | Endpoint | Rol |
|---------|----------|-----|
| POST | `/categoria` | ADMIN |
| GET | `/categoria` | Público |
| PUT | `/categoria/{id}` | ADMIN |
| DELETE | `/categoria/{id}` | ADMIN |

---

## 📦 Productos

| Método | Endpoint |
|---------|----------|
| GET | `/productos` |
| GET | `/productos/{id}` |
| GET | `/productos/paginado` |
| POST | `/productos` |
| PUT | `/productos/{id}` |
| DELETE | `/productos/{id}` |

---

## 🛒 Pedidos

| Método | Endpoint |
|---------|----------|
| POST | `/pedidos` |
| GET | `/pedidos` |
| GET | `/pedidos/{id}` |
| GET | `/pedidos/admin` |

---

# 🛡️ Seguridad

- JWT Authentication
- Spring Security
- Roles:
    - ADMIN
    - CLIENTE
- Endpoints protegidos
- Password cifrada con BCrypt

---

# ✅ Validaciones

Implementadas mediante:

```java
@Valid
@NotBlank
@NotNull
@Positive
@Size
@Email
```

---

# ⚠️ Manejo Global de Errores

La API implementa:

- ResourceNotFoundException
- BadRequestException
- StockInsuficienteException
- GlobalExceptionHandler

Respuesta estándar:

```json
{
  "timestamp": "2026-01-01T18:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Stock insuficiente"
}
```

---

# 📦 Respuestas Estandarizadas

Respuestas exitosas mediante:

```java
ApiResponse<T>
```

Errores mediante:

```java
ErrorResponse
```

---

# 📑 Paginación

Ejemplo:

```
GET /productos/paginado?page=0&size=5
```

---

# 📝 Logging

Implementado con:

- SLF4J
- LoggerFactory

Niveles utilizados:

- INFO
- WARN
- ERROR

---

# 🧪 Testing

El proyecto incorpora pruebas automatizadas utilizando:

- JUnit 5
- Mockito
- MockMvc
- Spring Security Test

## Tests implementados

### Service

- ✅ ProductoServiceImplTest
- ✅ PedidoServiceImplTest

### Controller

- ✅ ProductoControllerTest
- ✅ PedidoControllerTest

Se validan:

- Creación de productos
- Gestión de pedidos
- Excepciones
- Validaciones
- Roles
- Seguridad
- Endpoints REST

---

# 📊 Modelo de Datos

```text
Usuario
   │
   ├──────────────┐
   │              │
Pedido        Categoria
   │              │
   │              │
DetallePedido   Producto
       │
       └──────────────► Producto
```

---

# 🛠️ Requisitos Previos

- Java 21+
- Maven 3.9+
- MySQL
- Eclipse IDE / IntelliJ IDEA
- Postman

---

# ⚙️ Configuración del Proyecto

## Clonar repositorio

```bash
git clone https://github.com/usuario/API-E-commerce_Pro_Avanzado_con_Test.git
```

---

## Instalar dependencias

```bash
mvn clean install
```

---

## Ejecutar

```bash
mvn spring-boot:run
```

---

# 🧪 Probar la API con Postman

Orden recomendado:

1. Registrar usuario.
2. Login.
3. Obtener JWT.
4. Crear categorías.
5. Crear productos.
6. Consultar productos.
7. Crear pedidos.
8. Consultar pedidos.
9. Validar control de stock.
10. Probar permisos ADMIN / CLIENTE.

---

# 📈 Características Implementadas

- ✅ Arquitectura en capas
- ✅ DTOs
- ✅ JWT
- ✅ Roles
- ✅ Spring Security
- ✅ CRUD completo
- ✅ Control de stock
- ✅ Transacciones
- ✅ Validaciones automáticas
- ✅ Manejo global de errores
- ✅ ApiResponse
- ✅ ErrorResponse
- ✅ Logging profesional
- ✅ Paginación
- ✅ Filtros dinámicos
- ✅ Testing con JUnit
- ✅ Mockito
- ✅ MockMvc

---

# 👨‍💻 Autor

**Alejandro Collado Severiano**

Backend Java Developer

---
