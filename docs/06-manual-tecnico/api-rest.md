# API REST — HomeStore - WIP

Documentación de los endpoints REST. La colección Postman está en [`postman/`](../../postman/).

## Base URL

- **Local:** `http://localhost:8081`
- **Producción:** `https://homestore.onrender.com`

## Autenticación

Los endpoints `/api/**` requieren Basic Auth o token JWT.

```text
Authorization: Basic dXN1YXJpbzpwYXNzd29yZA==
```

---

## Endpoints

### `GET /api/mascotas`

Lista todas las mascotas activas del usuario autenticado.

**Request:**

```http
GET /api/mascotas HTTP/1.1
Authorization: Basic dXN1YXJpbzpwYXNzd29yZA==
```

**Response 200 OK:**

```json
[
  {
    "id": 1,
    "nombre": "Firulais",
    "especie": "Perro",
    "raza": "Labrador",
    "fechaNacimiento": "2020-03-15",
    "pesoKg": 25.5
  },
  {
    "id": 2,
    "nombre": "Michi",
    "especie": "Gato",
    "raza": "Siames",
    "fechaNacimiento": "2021-06-20",
    "pesoKg": 4.2
  }
]
```

**Errores:**

- `401 Unauthorized` — no se envió token / credenciales inválidas.

---

### `POST /api/citas`

Crea una nueva cita.

**Request:**

```http
POST /api/citas HTTP/1.1
Content-Type: application/json
Authorization: Basic dXN1YXJpbzpwYXNzd29yZA==

{
  "mascotaId": 1,
  "veterinarioId": 3,
  "fechaHora": "2025-09-15T10:00:00",
  "motivo": "Control anual"
}
```

**Response 201 Created:**

```json
{
  "id": 42,
  "mascotaId": 1,
  "veterinarioId": 3,
  "fechaHora": "2025-09-15T10:00:00",
  "estado": "PENDIENTE",
  "motivo": "Control anual",
  "fechaCreacion": "2025-09-01T14:23:45"
}
```

**Errores:**

- `400 Bad Request` — datos inválidos en el body.
- `409 Conflict` — el veterinario ya tiene una cita en ese horario.

---

## Pruebas

Importar `postman/homestore-api.postman_collection.json` en Postman para probar todos los endpoints con ejemplos.

## Status codes usados

| Código | Significado |
| --- | --- |
| 200 | OK |
| 201 | Created (POST exitoso) |
| 400 | Bad Request (datos inválidos) |
| 401 | Unauthorized (auth faltante) |
| 403 | Forbidden (sin permisos) |
| 404 | Not Found |
| 409 | Conflict |
| 500 | Internal Server Error |
