# 🗃️ Diagrama Entidad-Relación

Modelo de la base de datos del sistema VetCare.

## 📄 Archivos en esta carpeta

| Archivo | Contenido |
|---|---|
| `diagrama-er.png` | Diagrama visual (placeholder en este ejemplo) |
| `modelo-relacional.md` | Descripción textual de tablas y relaciones |
| `diagrama-er.mwb` | Archivo nativo de MySQL Workbench (opcional) |

## 📊 Resumen del modelo

**8 tablas** (cumple el mínimo requerido por el enunciado):

| # | Tabla | Tipo |
|---|---|---|
| 1 | `usuario` | Entidad principal |
| 2 | `rol` | Catálogo |
| 3 | `cliente` | Entidad principal |
| 4 | `mascota` | Entidad principal |
| 5 | `especie` | Catálogo |
| 6 | `veterinario` | Entidad principal |
| 7 | `servicio` | Entidad principal |
| 8 | `cita` | **Transaccional** |
| 9 | `cita_servicio` | Tabla puente (N:M entre cita y servicio) |
| 10 | `factura` | **Transaccional** (derivada de cita) |

> En el repo real, ver `diagrama-er.png` para la representación visual.

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
