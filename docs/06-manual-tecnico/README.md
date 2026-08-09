# API REST - HomeStore

Actualmente HomeStore utiliza una arquitectura MVC basada en Spring Boot y Thymeleaf.

La interacción con el sistema se realiza mediante controladores MVC (`@Controller`) y vistas renderizadas con Thymeleaf.

## Arquitectura utilizada

- Spring Boot
- Spring MVC
- Thymeleaf
- Spring Data JPA
- Spring Security
- MySQL

## Estado actual

En la versión actual del proyecto no se implementan servicios REST públicos (`@RestController`).

Todas las operaciones del sistema se realizan mediante formularios HTML y controladores MVC.

## Funcionalidades implementadas

- Registro de usuarios.
- Inicio de sesión.
- Gestión de productos.
- Gestión de categorías.
- Gestión de proveedores.
- Carrito de compras.
- Checkout.
- Confirmación de compra.
- Gestión de inventario.

## Futuras implementaciones

En futuras versiones podrán incorporarse servicios REST para:

- Productos.
- Inventario.
- Promociones.
- Reportes.
- Opiniones.
- Soporte técnico.