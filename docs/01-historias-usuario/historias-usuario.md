# Historias de Usuario — VetCare

> **Proyecto:** VetCare — Sistema de Gestión Veterinaria
> **Cliente potencial:** Clínicas veterinarias pequeñas y medianas
> **Total de historias:** 20 (muestra mínima requerida)

## 🧭 Índice

- [HU-01 a HU-05: Módulo de Usuarios](#módulo-de-usuarios)
- [HU-06 a HU-10: Módulo de Mascotas](#módulo-de-mascotas)
- [HU-11 a HU-14: Módulo de Citas (transaccional)](#módulo-de-citas-transaccional)
- [HU-15 a HU-17: Módulo de Servicios](#módulo-de-servicios)
- [HU-18 a HU-20: Módulo de Reportes y API](#módulo-de-reportes-y-api)

---

## Módulo de Usuarios

### HU-01 — Registro de cliente
**Como** cliente nuevo,
**quiero** registrarme en el sistema con correo y contraseña,
**para** poder agendar citas para mis mascotas.

**Criterios de aceptación:**
- El correo debe ser único en el sistema.
- La contraseña debe tener mínimo 8 caracteres.
- La contraseña se almacena cifrada con BCrypt.
- Al registrarse, el rol asignado por defecto es `CLIENTE`.
- Se muestra mensaje de confirmación.

---

### HU-02 — Inicio de sesión
**Como** usuario registrado,
**quiero** iniciar sesión con mi correo y contraseña,
**para** acceder a las funcionalidades del sistema según mi rol.

**Criterios de aceptación:**
- Si las credenciales son correctas, redirige al dashboard correspondiente.
- Si son incorrectas, muestra mensaje de error sin revelar cuál campo falló.
- Se gestiona la sesión con Spring Security.

---

### HU-03 — Cierre de sesión
**Como** usuario autenticado,
**quiero** cerrar sesión,
**para** proteger mi cuenta cuando uso una computadora compartida.

**Criterios de aceptación:**
- Al cerrar sesión, el usuario es redirigido al login.
- La sesión queda invalidada.

---

### HU-04 — Protección de rutas según rol
**Como** administrador,
**quiero** que solo los usuarios con rol `ADMIN` puedan acceder a la gestión de usuarios,
**para** mantener la seguridad del sistema.

**Criterios de aceptación:**
- Un cliente que intente acceder a `/admin/**` recibe 403 Forbidden.
- Spring Security maneja la autorización con `@PreAuthorize`.

---

### HU-05 — Gestión de roles (admin)
**Como** administrador,
**quiero** asignar y cambiar roles a los usuarios,
**para** controlar quién puede hacer qué en el sistema.

**Criterios de aceptación:**
- Solo el admin ve la lista de usuarios.
- Puede cambiar el rol entre: ADMIN, VETERINARIO, RECEPCIONISTA, CLIENTE.

---

## Módulo de Mascotas

### HU-06 — Registrar mascota nueva
**Como** cliente autenticado,
**quiero** registrar a mi mascota con sus datos,
**para** que la clínica tenga su historial.

**Criterios de aceptación:**
- Datos requeridos: nombre, especie, raza, fecha de nacimiento, peso.
- La mascota queda asociada al cliente que la registra.

---

### HU-07 — Listar mis mascotas
**Como** cliente,
**quiero** ver el listado de mis mascotas registradas,
**para** seleccionar una al agendar una cita.

**Criterios de aceptación:**
- Solo muestra las mascotas del cliente autenticado.
- Lista en formato responsive con cards.

---

### HU-08 — Ver detalle de mascota
**Como** cliente o veterinario,
**quiero** ver el detalle completo de una mascota,
**para** consultar su historial médico.

**Criterios de aceptación:**
- Muestra: datos básicos, citas pasadas, tratamientos.
- El veterinario ve todas las mascotas; el cliente solo las suyas.

---

### HU-09 — Editar datos de mascota
**Como** cliente,
**quiero** actualizar el peso o datos de mi mascota,
**para** mantener la información al día.

**Criterios de aceptación:**
- Solo el dueño y el admin pueden editar.
- Se valida que los datos sean correctos antes de guardar.

---

### HU-10 — Dar de baja una mascota
**Como** cliente,
**quiero** marcar una mascota como inactiva,
**para** que ya no aparezca en mi lista de mascotas activas.

**Criterios de aceptación:**
- La mascota NO se borra de la BD (preserva historial).
- Cambia un flag `activa = false`.
- Se solicita confirmación antes de la acción.

---

## Módulo de Citas (transaccional)

### HU-11 — Agendar una cita
**Como** cliente,
**quiero** agendar una cita para mi mascota en una fecha y hora específica,
**para** llevarla a consulta.

**Criterios de aceptación:**
- Se valida que el horario esté disponible (no haya conflictos).
- Se selecciona: mascota, fecha, hora, motivo, veterinario.
- La cita queda en estado `PENDIENTE`.

---

### HU-12 — Ver agenda del veterinario
**Como** veterinario,
**quiero** ver mi agenda del día y la semana,
**para** organizar mis consultas.

**Criterios de aceptación:**
- Muestra las citas asignadas al veterinario autenticado.
- Filtros por fecha y estado (pendiente, confirmada, completada).

---

### HU-13 — Cambiar estado de una cita
**Como** veterinario o recepcionista,
**quiero** cambiar el estado de una cita (confirmar, completar, cancelar),
**para** reflejar lo que pasó.

**Criterios de aceptación:**
- Estados válidos: PENDIENTE → CONFIRMADA → COMPLETADA / CANCELADA.
- Solo permite transiciones válidas.
- Al completar una cita, se genera la factura automáticamente.

---

### HU-14 — Cancelar mi cita
**Como** cliente,
**quiero** cancelar una cita futura,
**para** liberar el espacio si no puedo asistir.

**Criterios de aceptación:**
- Solo se pueden cancelar citas PENDIENTE o CONFIRMADA.
- Se pide confirmación antes.
- El cliente recibe notificación de la cancelación.

---

## Módulo de Servicios

### HU-15 — Listar servicios disponibles
**Como** cliente,
**quiero** ver los servicios y precios que ofrece la clínica,
**para** decidir qué solicitar.

**Criterios de aceptación:**
- Muestra: nombre, descripción, precio, duración estimada.
- Lista pública (no requiere login).

---

### HU-16 — Crear/editar servicios (admin)
**Como** administrador,
**quiero** crear y editar los servicios que ofrece la clínica,
**para** mantener actualizado el catálogo.

**Criterios de aceptación:**
- CRUD completo (Create, Read, Update, Delete).
- No se permite eliminar un servicio si tiene citas históricas (soft delete).

---

### HU-17 — Asociar servicios a una cita
**Como** veterinario,
**quiero** registrar qué servicios brindé en una cita,
**para** generar la facturación correcta.

**Criterios de aceptación:**
- Puede agregar varios servicios a una misma cita.
- El total de la factura se calcula automáticamente.

---

## Módulo de Reportes y API

### HU-18 — Reporte de ingresos del mes
**Como** administrador,
**quiero** ver un reporte de ingresos por mes,
**para** evaluar el desempeño financiero de la clínica.

**Criterios de aceptación:**
- Muestra: total facturado, número de citas atendidas, top de servicios.
- Filtros por rango de fechas.

---

### HU-19 — API: consultar mascotas (REST)
**Como** desarrollador de la app móvil,
**quiero** consultar las mascotas vía API REST,
**para** mostrarlas en la app del cliente.

**Criterios de aceptación:**
- Endpoint `GET /api/mascotas`.
- Responde con JSON.
- Requiere autenticación.

---

### HU-20 — API: crear cita (REST)
**Como** desarrollador de la app móvil,
**quiero** crear una cita vía API REST,
**para** que los clientes puedan agendar desde el móvil.

**Criterios de aceptación:**
- Endpoint `POST /api/citas`.
- Valida los datos en el body.
- Responde con `201 Created` y el ID de la cita generada.
- Devuelve `400 Bad Request` si los datos son inválidos.

---

## ✅ Resumen

- **Total:** 20 historias de usuario
- **Roles cubiertos:** Cliente, Veterinario, Recepcionista, Administrador
- **Módulos:** Usuarios, Mascotas, Citas (transaccional), Servicios, Reportes, API REST
- **Cumple requisito:** Módulo de Usuarios (4.2), CRUD principal (4.3), Módulo Transaccional (4.4), API REST (4.10)
