# API REST - HomeStore - WIP

Documentación de los endpoints REST de HomeStore.

La colección de Postman se encuentra en [`postman/`](../../postman/).

## Base URL

- **Local:** `http://localhost:8081`

---

## Autenticación

HomeStore utiliza Spring Security para controlar el acceso a las funcionalidades protegidas del sistema.

La configuración de seguridad de los endpoints `/api/**` deberá mantenerse de acuerdo con los roles y permisos definidos en HomeStore.

---

## Endpoints

### `GET /api/productos`

Obtiene la lista de productos registrados en HomeStore.

**Request:**

```http
GET /api/productos HTTP/1.1
Host: localhost:8081
Accept: application/json
```

**Response 200 OK:**

```json
[
  {
    "productId": 1,
    "name": "Silla de oficina",
    "description": "Silla ergonómica para oficina",
    "price": 45000.00,
    "costPrice": 30000.00,
    "stock": 10
  },
  {
    "productId": 2,
    "name": "Mesa auxiliar",
    "description": "Mesa auxiliar para sala",
    "price": 35000.00,
    "costPrice": 22000.00,
    "stock": 8
  }
]
```

**Errores posibles:**

- `401 Unauthorized` - el usuario no está autenticado cuando el endpoint requiere autenticación.
- `403 Forbidden` - el usuario está autenticado, pero no tiene permisos para acceder al recurso.
- `500 Internal Server Error` - ocurrió un error interno en el servidor.

---

### `POST /api/productos`

Registra un nuevo producto en HomeStore.

**Request:**

```http
POST /api/productos HTTP/1.1
Host: localhost:8081
Content-Type: application/json

{
  "name": "Lámpara de escritorio",
  "description": "Lámpara LED para escritorio",
  "price": 18000.00,
  "costPrice": 12000.00,
  "stock": 15
}
```

**Response 201 Created:**

```json
{
  "productId": 3,
  "name": "Lámpara de escritorio",
  "description": "Lámpara LED para escritorio",
  "price": 18000.00,
  "costPrice": 12000.00,
  "stock": 15
}
```

**Errores posibles:**

- `400 Bad Request` - los datos enviados son inválidos o están incompletos.
- `401 Unauthorized` - el usuario no está autenticado cuando el endpoint requiere autenticación.
- `403 Forbidden` - el usuario no tiene permisos para crear productos.
- `500 Internal Server Error` - ocurrió un error interno en el servidor.

---

## Pruebas

Para probar los endpoints REST se puede utilizar Postman.

La colección correspondiente a HomeStore deberá mantenerse en:

```text
postman/homestore-api.postman_collection.json
```

La colección debe contener solicitudes relacionadas únicamente con las funcionalidades de HomeStore.

---

## Status codes usados

| Código | Significado |
| ------ | ----------- |
| 200 | OK |
| 201 | Created - recurso creado correctamente |
| 400 | Bad Request - datos inválidos |
| 401 | Unauthorized - autenticación requerida |
| 403 | Forbidden - usuario sin permisos suficientes |
| 404 | Not Found - recurso no encontrado |
| 409 | Conflict - conflicto con el estado actual del recurso |
| 500 | Internal Server Error - error interno del servidor |

---

## Estado

La API REST se encuentra en proceso de implementación.

Los endpoints iniciales definidos para HomeStore son:

- `GET /api/productos`
- `POST /api/productos`

Estos endpoints permiten cumplir con la separación entre las vistas tradicionales de Thymeleaf y los servicios REST que devuelven información en formato JSON.