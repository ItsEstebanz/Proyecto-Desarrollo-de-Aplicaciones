# 📮 Colección Postman

## 📄 Archivos

| Archivo | Contenido |
| --- | --- |
| `homestore-api.postman_collection.json` | Colección importable a Postman con todos los endpoints REST |

## Cómo importar

1. Abrir Postman.
2. **File → Import** → seleccionar `homestore-api.postman_collection.json`.
3. Aparece la colección "HomeStore API".
4. Editar la variable `base_url` para apuntar a localhost o producción.

## Variables

| Variable | Valor por defecto |
| --- | --- |
| `base_url` | `http://localhost:8081` |

Para apuntar a producción, cambiar a `https://homestore.onrender.com`.

## Endpoints incluidos

| Método | Ruta | Descripción |
| --- | --- | --- |
| GET | `/api/productos` | Lista todos los productos |
| GET | `/api/productos/{id}` | Obtiene un producto por id (404 si no existe) |
| POST | `/api/productos` | Crea un producto (requiere `categoryId`/`supplierId` válidos, 400 si no lo son) |
| GET | `/api/categorias` | Lista todas las categorías |
| GET | `/api/categorias/{id}/productos` | Lista los productos de una categoría (404 si no existe) |
| GET | `/api/sucursales` | Lista las sucursales físicas activas |

Todos verificados manualmente contra una base de datos real (esquema + datos de prueba) antes de publicarse en esta colección.
