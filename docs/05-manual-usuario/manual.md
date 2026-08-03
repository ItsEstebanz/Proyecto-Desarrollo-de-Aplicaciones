# Manual de Usuario - HomeStore - WIP

## 1. Inicio

Acceder a [https://homestore.onrender.com](https://homestore.onrender.com) desde un navegador moderno (Chrome, Firefox, Edge).

> *(Aquí va una captura de la página principal del sistema)*

## 2. Registro de usuario nuevo

1. Click en **"Registrarse"** en la barra superior.
2. Completar el formulario:
   - Correo electrónico (debe ser único)
   - Contraseña (mínimo 8 caracteres)
   - Nombre y apellidos
   - Teléfono
3. Click en **"Crear cuenta"**.
4. Se inicia sesión automáticamente.

## 3. Iniciar sesión

1. Click en **"Iniciar sesión"**.
2. Ingresar correo y contraseña.
3. El sistema redirige al dashboard según el rol.

## 4. Para Clientes

### 4.1 Registrar una mascota

1. En el dashboard, click en **"Mis mascotas" → "Agregar mascota"**.
2. Completar los datos.
3. Guardar.

### 4.2 Agendar una cita

1. **"Citas" → "Nueva cita"**.
2. Seleccionar mascota.
3. Seleccionar fecha y hora disponibles.
4. Seleccionar veterinario.
5. Escribir motivo de la consulta.
6. Confirmar.

### 4.3 Ver historial

En **"Mis mascotas"**, click sobre una mascota para ver su historial completo.

## 5. Para Veterinarios

### 5.1 Ver agenda del día

1. Click en **"Mi agenda"**.
2. Por defecto muestra las citas del día.

### 5.2 Atender una cita

1. Click sobre una cita pendiente.
2. Cambiar estado a **"Confirmada"**.
3. Al terminar la consulta:
   - Registrar observaciones.
   - Seleccionar servicios brindados.
   - Cambiar estado a **"Completada"**.
   - La factura se genera automáticamente.

## 6. Para Administradores

### 6.1 Gestionar usuarios

`/admin/usuarios` - lista, edita y asigna roles.

### 6.2 Gestionar servicios

`/admin/servicios` - CRUD completo del catálogo.

### 6.3 Reportes

`/admin/reportes` - ingresos mensuales, citas por veterinario, etc.

## 7. Cambiar de idioma

En la esquina superior derecha hay un selector ES / EN.

## 8. Soporte

📧 [soporte@homestore.com](mailto:soporte@homestore.com)
📱 +506 8888-9999
