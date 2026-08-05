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

Este documento refleja las rutas disponibles actualmente en HomeStore. Los módulos de registro, productos, carrito, checkout y confirmación de compra ya cuentan con un flujo funcional. Los módulos de inventario, promociones y reportes continúan pendientes de implementación completa.

- carrito y sus artículos;
- checkout, pago, entrega y factura de venta;
- movimientos de inventario;
- promociones y códigos de descuento;
- reseñas, tickets de soporte y auditoría.

---

## Flujo de compra implementado

```text
Inicio
  ↓
Registro o inicio de sesión
  ↓
Listado de productos
  ↓
Agregar producto al carrito
  ↓
Visualizar carrito
  ↓
Aumentar, disminuir o eliminar productos
  ↓
Continuar al pago
  ↓
Checkout
  ↓
Seleccionar método de pago
  ↓
Seleccionar método de envío
  ↓
Seleccionar sucursal
  ↓
Confirmar compra
  ↓
Compra exitosa
