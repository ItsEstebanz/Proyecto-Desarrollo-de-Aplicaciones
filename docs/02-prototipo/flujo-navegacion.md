# 🔀 Flujo de Navegación

Este documento refleja las rutas disponibles actualmente en HomeStore. Los módulos de carrito, ventas, inventario, promociones y reportes están definidos en el modelo de datos, pero todavía no tienen flujo implementado.

```text
                              ┌─────────────┐
                              │   HOME (/)  │
                              │   pública   │
                              └──────┬──────┘
                                     │
       ┌─────────────────────┬───────┼─────────┬────────────────┐
       ▼                     ▼       ▼         ▼                ▼
 ┌───────────┐        ┌──────────┐   │   ┌──────────┐      ┌──────────┐
 │POST /login│        │/registro │   │   │/nosotros │      │/contacto │
 └─────┬─────┘        └──────────┘   │   └──────────┘      └──────────┘
       │                             │
       ▼                             ▼
 ┌─────────────┐        ┌───────────────────────────────┐
 │  /inicio    │        │ Navegación pública            │
 │ autenticada │        │ /categories, /cart, /location │
 └─────────────┘        │ /ubicacion, /products         │
                        └───────────────────────────────┘

                              ┌────────────────────┐
                              │    /productos      │
                              │ listado con datos  │
                              └─────────┬──────────┘
                                        │
                       ┌────────────────┼────────────────┐
                       ▼                ▼                ▼
             ┌────────────────┐ ┌─────────────┐ ┌─────────────────┐
             │/productos/nuevo│ │/editar/{id} │ │/eliminar/{id}   │
             │ formulario     │ │ formulario  │ │ elimina y vuelve│
             └───────┬────────┘ └───────┬─────┘ └─────────────────┘
                     └──────────┬───────┘
                                ▼
                    ┌─────────────────────────┐
                    │ POST /productos/guardar │
                    │ → redirección a listado │
                    └─────────────────────────┘
```

## Flujos implementados

### 1. Inicio de sesión

```text
HOME → POST /login → credenciales válidas → /inicio
                     credenciales inválidas → /?error=true
```

`/inicio` es la única ruta que la configuración actual exige autenticar. El registro solo muestra el formulario; todavía no guarda usuarios ni inicia sesión automáticamente.

### 2. Gestión de productos

```text
/productos → /productos/nuevo → POST /productos/guardar → /productos

/productos → /productos/editar/{id} → POST /productos/guardar → /productos

/productos → /productos/eliminar/{id} → /productos
```

El formulario de producto utiliza las categorías y proveedores registrados en la base de datos. La autorización específica por rol y la conversión de la eliminación a una solicitud segura se documentarán al implementar el módulo de administración.

## Rutas pendientes

El modelo relacional ya contempla los siguientes flujos, pero no se presentan como funcionalidad disponible todavía (WIP):

- carrito y sus artículos;
- checkout, pago, entrega y factura de venta;
- movimientos de inventario;
- promociones y códigos de descuento;
- reseñas, tickets de soporte y auditoría.
