# 🗃️ Base de datos

## Archivos

| Archivo | Propósito |
| --- | --- |
| [`schema.sql`](./schema.sql) | Crea `homestoredb` y las 19 tablas del modelo de ventas. (DDL) |
| [`seed-data.sql`](./seed-data.sql) | Insertar datos de prueba. (DML) |

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

El script usa `CREATE ... IF NOT EXISTS`, por lo que puede ejecutarse de nuevo sin intentar recrear tablas existentes. Si se necesita cambiar una tabla que ya existe, debe prepararse una migración explícita; el script no altera estructuras ya creadas.

## Relación con Spring Boot

La aplicación utiliza `spring.jpa.hibernate.ddl-auto=validate`. Por tanto, no crea ni actualiza tablas al arrancar: el esquema debe aplicarse antes de iniciar la aplicación. Las entidades actuales usan las tablas `role`, `user`, `category`, `supplier` y `product`.

El detalle completo de las tablas y relaciones está en [`docs/03-diagrama-er/modelo-relacional.md`](../docs/03-diagrama-er/modelo-relacional.md).
