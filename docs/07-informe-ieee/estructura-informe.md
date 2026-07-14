# Esqueleto del Informe IEEE — HomeStore

Esto es un esquema de referencia. El informe final debe entregarse en PDF formato IEEE.
    
---

## Título

**HomeStore — Sistema Web de Gestión de Ventas e Inventario**

## Autores

Josué Azofeifa Chavarria, Alondra Matamoros Matamoros, Luis Sanchez Calderon, Esteban Ricardo Solís Campos
Universidad Fidélitas, Escuela de Informática
{eazofeifa50055, amatamoros70871, lsanchez70654, esolis50753}@ufide.ac.cr

Curso: CS-403 — Desarrollo de Aplicaciones Web y Patrones
Profesor: David Barquero

---

## Abstract (español)

HomeStore es un sistema web desarrollado con Spring Boot 3.3.5, Java 21, Thymeleaf y MySQL, diseñado para centralizar la gestión de inventario y ventas de pequeñas y medianas tiendas. El sistema automatiza el registro de productos, el control de existencias, la actualización del inventario tras cada venta y la generación de reportes en tiempo real. Este artículo presenta la metodología de desarrollo, las decisiones arquitectónicas y los resultados obtenidos al implementar las funcionalidades requeridas. *(máx. 250 palabras — falta de completar con resultados de Avance 2/3)*

## Abstract (English)

HomeStore is a web system built with Spring Boot 3.3.5, Java 21, Thymeleaf, and MySQL, designed to centralize inventory and sales management for small and medium-sized stores. The system automates product registration, stock control, automatic inventory updates after each sale, and real-time reporting. This article presents the development methodology, architectural decisions, and results obtained while implementing the required functionality. *(max 250 words — pending completion with Avance 2/3 results)*

## Keywords

`Spring Boot`, `MVC`, `Thymeleaf`, `JPA`, `MySQL`, `Bootstrap 5`, `MVC`, `gestión de inventario`, `sistema de ventas`, `web application`, `REST API`

---

## I. Introducción

Muchas pequeñas y medianas tiendas todavía controlan sus productos y ventas de forma manual o mediante hojas de cálculo. Esta práctica genera errores de inventario, pérdida de información, dificultad para conocer las existencias reales y, en general, un control ineficiente del proceso de ventas.

HomeStore nace como respuesta a ese problema: una solución web que centraliza el inventario y las ventas de un negocio, cubriendo el registro de productos, el control de existencias, la actualización automática del inventario después de cada venta y la consulta de reportes en tiempo real.

El objetivo del sistema es optimizar los procesos administrativos del negocio, reducir la incidencia de errores humanos, mejorar la organización interna y apoyar la toma de decisiones con información confiable y actualizada.

## II. Marco Teórico

### A. Spring Boot y el patrón MVC

[Descripción del patrón con cita a documentación oficial]

### B. Hibernate y JPA

[Descripción del ORM]

### C. Spring Security

[Mecanismos de autenticación y autorización]

### D. Bootstrap 5 y diseño responsive

[Grid system, mobile-first]

## III. Metodología

El desarrollo siguió la metodología ágil con sprints semanales. Cada integrante trabajó en su feature branch y se hacían merges al main con revisión de código.

### A. Herramientas usadas

- IDE: Visual Studio Code
- Versionado: Git + GitHub
- Diseño: Figma
- Testing de API: Postman

### B. Cronograma

[Tabla del cronograma]

## IV. Desarrollo

### A. Arquitectura por capas

[Diagrama de capas + descripción]

### B. Modelo de datos

[Diagrama ER]

### C. Implementación de la seguridad

[Configuración de Spring Security]

### D. API REST

[Endpoints implementados]

## V. Resultados

### A. Funcionalidades implementadas

[Lista con capturas]

### B. Capturas de pantalla

[Imágenes del sistema]

### C. Métricas

- Líneas de código: ~X LOC
- Cantidad de commits: x (en 14 semanas)
- Cobertura de tests: x%

## VI. Conclusiones

[Pendiente]

## Referencias

[1] Oracle Corporation, "Java Platform, Standard Edition 21 Documentation," Oracle Corporation, 2025. [En línea]. Disponible: https://docs.oracle.com/en/java/javase/21/

[2] Spring, "Spring Boot Reference Documentation," VMware, Inc., 2024. [En línea]. Disponible: https://docs.spring.io/spring-boot/docs/current/reference/html/

[3] Bootstrap Team, "Bootstrap 5," Bootstrap, 2025. [En línea]. Disponible: https://getbootstrap.com/docs/5.3/getting-started/introduction/

[4] Hibernate, "Hibernate ORM Documentation," 2025. [En línea]. Disponible: https://hibernate.org/orm/documentation/

[5] Spring, "Spring Data JPA," VMware, Inc., 2026. [En línea]. Disponible: https://spring.io/projects/spring-data-jpa

[6] Hibernate, "Hibernate ORM," 2026. [En línea]. Disponible: https://hibernate.org/orm

[7] Spring, "Spring Security," VMware, Inc., 2026. [En línea]. Disponible: https://spring.io/projects/spring-security

[8] Git Project, "Git reference manual," 2026. [En línea]. Disponible: https://git-scm.com/docs

[9] GitHub, Inc., "GitHub features," 2026. [En línea]. Disponible: https://github.com/features

[10] Figma, Inc., "Figma help center," 2026. [En línea]. Disponible: https://help.figma.com

[11] Postman, Inc., "Postman learning center," 2026. [En línea]. Disponible: https://learning.postman.com

[12] M. Fowler, "Inversion of Control Containers and the Dependency Injection pattern," 2004. [En línea]. Disponible: https://martinfowler.com/articles/injection.html
