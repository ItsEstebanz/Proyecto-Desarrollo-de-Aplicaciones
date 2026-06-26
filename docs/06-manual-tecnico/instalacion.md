# Instalación Local — VetCare

## Prerrequisitos

- Java JDK 21
- Maven 3.9+ (o usar el `mvnw` wrapper incluido)
- MySQL 8.0
- Git

## Paso a paso

### 1. Clonar el repositorio

```bash
git clone https://github.com/equipo/vetcare.git
cd vetcare
```

### 2. Crear la base de datos

Abrir MySQL Workbench y ejecutar:

```sql
CREATE DATABASE vetcaredb CHARACTER SET utf8mb4;
```

O desde terminal:

```bash
mysql -u root -p -e "CREATE DATABASE vetcaredb CHARACTER SET utf8mb4;"
```

### 3. Cargar el schema y los datos de prueba

```bash
mysql -u root -p vetcaredb < database/schema.sql
mysql -u root -p vetcaredb < database/seed-data.sql
```

> Hibernate también crea las tablas automáticamente al arrancar (`ddl-auto=update`), pero `schema.sql` es la referencia oficial.

### 4. Configurar las credenciales

Definir variables de entorno antes de correr:

**Linux/Mac:**
```bash
export DB_URL=jdbc:mysql://localhost:3306/vetcaredb
export DB_USERNAME=root
export DB_PASSWORD=tu_password
```

**Windows PowerShell:**
```powershell
$env:DB_URL = "jdbc:mysql://localhost:3306/vetcaredb"
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = "tu_password"
```

### 5. Correr el proyecto

```bash
./mvnw spring-boot:run        # Linux/Mac
mvnw.cmd spring-boot:run      # Windows
```

### 6. Abrir el navegador

`http://localhost:8080`

## Usuarios de prueba precargados

| Rol | Correo | Password |
|---|---|---|
| Admin | admin@vetcare.com | admin123 |
| Veterinario | vet@vetcare.com | vet12345 |
| Cliente | cliente@vetcare.com | cliente1 |

## Compilar el JAR de producción

```bash
./mvnw clean package -DskipTests
java -jar target/vetcare-0.0.1-SNAPSHOT.jar
```
