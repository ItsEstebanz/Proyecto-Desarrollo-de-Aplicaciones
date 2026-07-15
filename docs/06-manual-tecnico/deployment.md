# Despliegue en Render.com

Cómo publicar VetCare en producción.

## Requisitos previos

- Cuenta en [https://render.com](https://render.com) (gratuita).
- Repositorio público en GitHub.
- Base de datos MySQL accesible desde internet (ej. **Aiven**, **Railway** o **Render Postgres** con migración).

## Paso a paso

### 1. Crear servicio Web Service en Render

1. Login en Render.com.
2. **New → Web Service**.
3. Conectar la cuenta de GitHub.
4. Seleccionar el repo `homestore`.
5. Configurar:
   - **Name:** homestore
   - **Environment:** Docker (o Java si está disponible)
   - **Region:** Oregon (gratis)
   - **Branch:** main
   - **Build Command:** `./mvnw clean package -DskipTests`
   - **Start Command:** `java -jar target/homestore-0.0.1-SNAPSHOT.jar`
   - **Instance Type:** Free

### 2. Configurar variables de entorno

En la pestaña **Environment** del servicio:

| Key | Value |
| --- | --- |
| `DB_URL` | `jdbc:mysql://[host-aiven]:[puerto]/homestoredb?useSSL=true` |
| `DB_USERNAME` | `avnadmin` (o el de tu BD) |
| `DB_PASSWORD` | `tu_password_seguro` |
| `JAVA_OPTS` | `-Xmx400m` |

### 3. Crear la BD en Aiven (alternativa gratis)

1. [https://aiven.io](https://aiven.io) → cuenta gratuita.
2. **Create service → MySQL → Free plan**.
3. Esperar que se aprovisione.
4. Copiar la cadena de conexión y crearla en Render como variables.

### 4. Inicializar el schema en producción

Desde MySQL Workbench conectándose al servidor de Aiven:

```sql
CREATE DATABASE homestoredb CHARACTER SET utf8mb4;
USE homestoredb;
SOURCE schema.sql;
SOURCE seed-data.sql;
```

### 5. Esperar el deploy

Render compila y arranca automáticamente en cada push a `main`.

### 6. URL final

`https://homestore.onrender.com`

> El plan free de Render duerme la app después de 15 min sin tráfico. El primer request luego de dormir tarda ~30 segundos en responder.

## Logs y monitoreo

- **Logs** → pestaña "Logs" en Render.
- **Métricas** → pestaña "Metrics".
- Alertas por correo configurables.
