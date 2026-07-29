# 🗃️ Base de datos

## Archivos

| Archivo | Propósito |
| --- | --- |
| `schema.sql` | Crear todas las tablas (DDL) |
| `seed-data.sql` | Insertar datos de prueba |

## Aplicación del esquema

Ejecutar primero `schema.sql` y, cuando esté listo, cargar los datos iniciales en una ejecución separada:

### Desde MySQL Workbench

1. File → Open SQL Script → seleccionar `schema.sql` → Ejecutar (rayo).
2. Repetir con `seed-data.sql`.

### Desde terminal

```bash
mysql -u root -p < database/schema.sql
mysql -u root -p homestoredb < database/seed-data.sql
```

## Hibernate vs `schema.sql`

Hibernate también crea las tablas a partir de las `@Entity` con `ddl-auto=update`. El `schema.sql` es la **referencia oficial** del esquema y es lo que se aplica en producción.
