# 📝 Historias de Usuario

Las historias de usuario de este proyecto describen el alcance actual de HomeStore: un sistema web para gestionar un catálogo de productos, categorías, proveedores y acceso de usuarios.

## ✅ Estado actual del proyecto

Actualmente el sistema ya cuenta con los siguientes elementos:

- Página principal y vistas informativas: inicio, nosotros, contacto y ubicación.
- Registro e inicio de sesión con autenticación basada en usuarios y roles.
- Área privada accesible tras iniciar sesión en `/inicio`.
- Catálogo de productos con operaciones CRUD desde `/productos`.
- Gestión de categorías y proveedores como parte del modelo del negocio.
- Navegación por vistas de productos, categorías y carrito.
- Diseño de base de datos orientado a ventas, promociones, inventario, pagos y soporte.
- Carrito de compras integrado y funcional, con validaciones de cantidad y montos acorde a stock.

## 📄 Archivos en esta carpeta

- [`historias-usuario.md`](./historias-usuario.md) - listado de historias del alcance actual y del alcance futuro.
- *(opcional)* `criterios-aceptacion.md` - criterios extendidos por historia

## 📋 Formato

Cada historia sigue el formato estándar:

> **Como** [rol del usuario]
> **quiero** [acción que puede realizar]
> **para** [beneficio que obtiene]

Y debe incluir **criterios de aceptación**: condiciones que deben cumplirse para considerar la historia terminada.

## 🎯 Roles del sistema

| Rol | Descripción | Estado actual |
| --- | --- | --- |
| **Cliente / comprador** | Puede navegar por el catálogo, registrarse, iniciar sesión y usar el carrito. | Implementado en la interfaz y en la autenticación. |
| **Administrador / gestor** | Puede gestionar productos, categorías y proveedores desde el módulo del catálogo. | Implementado en el CRUD de productos. |
| **Usuario autenticado** | Accede al área privada del sistema tras iniciar sesión. | Implementado. |

## Alcance siguiente

Las historias futuras del proyecto se enfocan en completar el flujo de ventas y operación, incluyendo:

- Implementar confirmación de compra.
- Incorporar métodos de pago y opciones de envío con cálculo de costos adicionales.
- Gestionar descuentos y promociones, incluyendo códigos de descuento aplicables durante la compra.
- Desarrollar módulos de administración para ajustar precios, gestionar promociones y revisar inventario.
- Crear vistas y acciones para reportes de ventas, ganancias o pérdidas, facturación y seguimiento de órdenes.
- Implementar soporte al cliente y gestión de opiniones o reseñas de usuarios.
- Completar la integración entre la base de datos y la interfaz para cubrir los módulos de ventas, auditoría y soporte definidos en el modelo del sistema.
