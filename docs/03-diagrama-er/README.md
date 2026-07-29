# 🗃️ Diagrama Entidad-Relación

El modelo entidad-relación de HomeStore representa la gestión de catálogo, usuarios, ventas, inventario, promociones y soporte al cliente.

## 📄 Archivos

| Archivo | Contenido |
| --- | --- |
| [`MER_SistemaVentas_v3.5.svg`](./MER_SistemaVentas_v3.5.svg) | Diagrama visual fuente del modelo de ventas. |
| [`modelo-relacional.md`](./modelo-relacional.md) | Tablas, relaciones y reglas de integridad. |
| [`../../database/schema.sql`](../../database/schema.sql) | Script MySQL 8 para crear la estructura. |

## 📊 Resumen del modelo

**8 tablas** (cumple el mínimo requerido por el enunciado):

> **Nota:** Esta es la representación visual. [MER_SistemaVentas_v3.5.svg](./MER_SistemaVentas_v3.5.svg)

## 🔗 Relaciones principales

- `usuario` 1:1 `cliente` / `veterinario` (un usuario puede ser cliente, vet o admin)
- `cliente` 1:N `mascota`
- `especie` 1:N `mascota`
- `mascota` 1:N `cita`
- `veterinario` 1:N `cita`
- `cita` N:M `servicio` (vía `cita_servicio`)
- `cita` 1:1 `factura`

## ✅ Cumplimiento de requisitos

- ✅ **Mínimo 8 tablas:** sí (10)
- ✅ **Relaciones 1:N:** sí (cliente-mascota, mascota-cita)
- ✅ **Relaciones N:M:** sí (cita-servicio)
- ✅ **Llaves primarias:** todas las tablas tienen PK
- ✅ **Llaves foráneas:** correctamente definidas
- ✅ **Integridad referencial:** ON DELETE CASCADE en `cita_servicio`, ON UPDATE CASCADE en FKs
- ✅ **Tabla transaccional:** `cita` y `factura`
