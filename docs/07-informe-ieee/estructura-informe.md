# Esqueleto del Informe IEEE — VetCare

Esto es un esquema de referencia. El informe final debe entregarse en PDF formato IEEE.

---

## Título

**VetCare: Sistema Web Transaccional para la Gestión de Clínicas Veterinarias**

## Autores

Juan Pérez, María Rodríguez, Carlos Jiménez
Universidad Fidélitas, Escuela de Informática
{juan.perez, maria.rod, carlos.jim}@ufide.ac.cr

---

## Abstract (español)

VetCare es un sistema web transaccional desarrollado con Spring Boot 3.3, MySQL y Bootstrap 5, diseñado para automatizar la gestión de clínicas veterinarias pequeñas y medianas. El sistema implementa un patrón MVC ampliado con capas de Service y Repository, autenticación basada en roles con Spring Security, internacionalización en español e inglés, y una API REST para integración con aplicaciones móviles. Este artículo presenta la metodología de desarrollo, las decisiones de diseño y los resultados obtenidos al implementar las funcionalidades requeridas. *(máx. 250 palabras)*

## Abstract (English)

VetCare is a transactional web system developed with Spring Boot 3.3, MySQL, and Bootstrap 5, designed to automate the management of small and medium veterinary clinics. The system implements an extended MVC pattern with Service and Repository layers, role-based authentication with Spring Security, Spanish-English internationalization, and a REST API for mobile integration. This article presents the development methodology, design decisions, and results obtained. *(max 250 words)*

## Keywords

`Spring Boot`, `MVC`, `JPA`, `Bootstrap 5`, `web application`, `clinic management`, `REST API`

---

## I. Introducción

Las clínicas veterinarias pequeñas suelen manejar sus citas y registros manualmente o con hojas de cálculo. Esto genera problemas de consistencia, pérdida de información y dificultad para escalar el servicio. VetCare propone una solución web moderna...

[1-2 párrafos más sobre el contexto y la motivación]

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
- Issue tracking: GitHub Issues
- Diseño: Figma
- Despliegue: Render.com

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

- Líneas de código: ~3,500 LOC
- Cantidad de commits: 230 (en 14 semanas)
- Cobertura de tests: 65%

## VI. Conclusiones

VetCare demuestra que es posible construir una aplicación web transaccional profesional usando exclusivamente el stack Java + Spring Boot + MySQL. Los principales aprendizajes fueron...

[2-3 párrafos]

## Referencias

[1] Spring Team, "Spring Boot Reference Documentation," 2025. [En línea]. Disponible: https://docs.spring.io/spring-boot/reference/

[2] M. Fowler, "Inversion of Control Containers and the Dependency Injection pattern," 2004. [En línea]. Disponible: https://martinfowler.com/articles/injection.html

[3] E. Evans, *Domain-Driven Design: Tackling Complexity in the Heart of Software*. Boston, MA: Addison-Wesley, 2003.

[4] Bootstrap Authors, "Bootstrap 5.3 Documentation," 2025. [En línea]. Disponible: https://getbootstrap.com/docs/5.3

[5] Hibernate Team, "Hibernate ORM User Guide," 2025. [En línea]. Disponible: https://hibernate.org/orm/documentation/

[Mínimo 5 — agregar más según las tecnologías usadas]
