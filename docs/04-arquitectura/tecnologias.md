# Stack Tecnológico - Justificación

| Tecnología | Versión | Por qué la elegimos |
| --- | --- | --- |
| Java | 21 | Última LTS, requerido por el curso. |
| Spring Boot | 3.3.5 | Framework backend obligatorio. Convención sobre configuración. |
| Spring Data JPA | 3.x | ORM con repositorios automáticos. |
| Hibernate | 6.x | Implementación de JPA, líder de la industria. |
| MySQL | 8.0 | BD relacional requerida por el curso. |
| Thymeleaf | 3.x | Motor de plantillas que se integra nativamente con Spring. |
| Bootstrap | 5.3 | Framework CSS responsive más usado. |
| Bootstrap Icons | 1.11 | Iconografía consistente. |
| Spring Security | 6.x | Manejo de autenticación y autorización. |
| BCrypt | (built-in) | Cifrado de contraseñas estándar. |
| Maven | 3.9+ | Gestor de dependencias estándar Java. |
| Git + GitHub | — | Control de versiones colaborativo. |
| Render.com | — | Despliegue cloud sin costo. |
| Postman | — | Documentación y testing de la API. |

## Tecnologías NO usadas (y por qué)

- **React / Angular** - el frontend se hace con Thymeleaf según requisito del curso.
- **Node.js** - el backend obligatorio es Java + Spring Boot.
- **Lombok** - opcional, lo evitamos para que los getters/setters sean visibles.

## Dependencias clave del `pom.xml`

```xml
<!-- Web + Thymeleaf + JPA + MySQL + Security -->
<dependency>spring-boot-starter-web</dependency>
<dependency>spring-boot-starter-thymeleaf</dependency>
<dependency>spring-boot-starter-data-jpa</dependency>
<dependency>spring-boot-starter-security</dependency>
<dependency>spring-boot-starter-validation</dependency>
<dependency>mysql-connector-j</dependency>
<dependency>thymeleaf-extras-springsecurity6</dependency>
```
