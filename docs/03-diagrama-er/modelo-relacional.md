# Modelo Relacional — HomeStore - WIP

Descripción textual de cada tabla con sus columnas, tipos y relaciones.

---

## Tabla: `rol`

| Columna | Tipo | Nulo | PK | FK | Descripción |
| --- | --- | --- | --- | --- | --- |
| id | BIGINT | NO | ✅ | | Llave primaria |
| nombre | VARCHAR(50) | NO | | | ADMIN, VETERINARIO, RECEPCIONISTA, CLIENTE |

---

## Tabla: `usuario`

| Columna | Tipo | Nulo | PK | FK | Descripción |
| --- | --- | --- | --- | --- | --- |
| id | BIGINT | NO | ✅ | | Llave primaria |
| correo | VARCHAR(150) | NO | | | UNIQUE |
| password | VARCHAR(60) | NO | | | BCrypt hash |
| activo | BOOLEAN | NO | | | default true |
| rol_id | BIGINT | NO | | ✅ rol(id) | Rol asignado |
| fecha_registro | DATETIME | NO | | | default NOW() |

---

## Tabla: `cliente`

| Columna | Tipo | Nulo | PK | FK | Descripción |
| --- | --- | --- | --- | --- | --- |
| id | BIGINT | NO | ✅ | | Llave primaria |
| usuario_id | BIGINT | NO | | ✅ usuario(id) | UNIQUE, 1:1 con usuario |
| nombre | VARCHAR(100) | NO | | | |
| apellidos | VARCHAR(100) | NO | | | |
| telefono | VARCHAR(20) | SÍ | | | |
| direccion | VARCHAR(255) | SÍ | | | |

---

## Tabla: `especie`

| Columna | Tipo | Nulo | PK | FK | Descripción |
| --- | --- | --- | --- | --- | --- |
| id | BIGINT | NO | ✅ | | Llave primaria |
| nombre | VARCHAR(50) | NO | | | Perro, Gato, Ave, Reptil, etc. |

---

## Tabla: `mascota`

| Columna | Tipo | Nulo | PK | FK | Descripción |
| --- | --- | --- | --- | --- | --- |
| id | BIGINT | NO | ✅ | | Llave primaria |
| nombre | VARCHAR(100) | NO | | | |
| especie_id | BIGINT | NO | | ✅ especie(id) | |
| raza | VARCHAR(100) | SÍ | | | |
| fecha_nacimiento | DATE | SÍ | | | |
| peso_kg | DECIMAL(5,2) | SÍ | | | |
| cliente_id | BIGINT | NO | | ✅ cliente(id) | Dueño |
| activa | BOOLEAN | NO | | | default true (soft delete) |

---

## Tabla: `veterinario`

| Columna | Tipo | Nulo | PK | FK | Descripción |
| --- | --- | --- | --- | --- | --- |
| id | BIGINT | NO | ✅ | | Llave primaria |
| usuario_id | BIGINT | NO | | ✅ usuario(id) | UNIQUE, 1:1 con usuario |
| nombre | VARCHAR(100) | NO | | | |
| especialidad | VARCHAR(100) | SÍ | | | |
| colegiatura | VARCHAR(50) | SÍ | | | Número de colegiado |

---

## Tabla: `servicio`

| Columna | Tipo | Nulo | PK | FK | Descripción |
| --- | --- | --- | --- | --- | --- |
| id | BIGINT | NO | ✅ | | Llave primaria |
| nombre | VARCHAR(100) | NO | | | |
| descripcion | TEXT | SÍ | | | |
| precio | DECIMAL(10,2) | NO | | | |
| duracion_min | INT | NO | | | Duración estimada |
| activo | BOOLEAN | NO | | | default true |

---

## Tabla: `cita` (transaccional)

| Columna | Tipo | Nulo | PK | FK | Descripción |
| --- | --- | --- | --- | --- | --- |
| id | BIGINT | NO | ✅ | | Llave primaria |
| mascota_id | BIGINT | NO | | ✅ mascota(id) | |
| veterinario_id | BIGINT | NO | | ✅ veterinario(id) | |
| fecha_hora | DATETIME | NO | | | |
| motivo | VARCHAR(255) | SÍ | | | |
| estado | VARCHAR(20) | NO | | | PENDIENTE, CONFIRMADA, COMPLETADA, CANCELADA |
| observaciones | TEXT | SÍ | | | Notas del veterinario |
| fecha_creacion | DATETIME | NO | | | default NOW() |

---

## Tabla: `cita_servicio` (N:M)

| Columna | Tipo | Nulo | PK | FK | Descripción |
| --- | --- | --- | --- | --- | --- |
| cita_id | BIGINT | NO | ✅ | ✅ cita(id) | |
| servicio_id | BIGINT | NO | ✅ | ✅ servicio(id) | |
| precio_aplicado | DECIMAL(10,2) | NO | | | Precio al momento de la cita |

PK compuesta: (`cita_id`, `servicio_id`)

---

## Tabla: `factura` (transaccional)

| Columna | Tipo | Nulo | PK | FK | Descripción |
| --- | --- | --- | --- | --- | --- |
| id | BIGINT | NO | ✅ | | Llave primaria |
| cita_id | BIGINT | NO | | ✅ cita(id) | UNIQUE, 1:1 con cita |
| subtotal | DECIMAL(10,2) | NO | | | |
| impuestos | DECIMAL(10,2) | NO | | | IVA 13% |
| total | DECIMAL(10,2) | NO | | | |
| fecha_emision | DATETIME | NO | | | |
| pagada | BOOLEAN | NO | | | default false |
