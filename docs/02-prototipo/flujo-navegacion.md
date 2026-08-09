# 🔀 Flujo de Navegación

Este documento describe el flujo de navegación implementado actualmente en **HomeStore**. El sistema cuenta con autenticación de usuarios, gestión de productos, carrito de compras, checkout y confirmación de compra. Los módulos de promociones, reportes y administración avanzada continúan en desarrollo.

```text
                               ┌───────────────┐
                               │   HOME (/)    │
                               │   Pública     │
                               └───────┬───────┘
                                       │
        ┌───────────────┬──────────────┼──────────────┬──────────────┐
        ▼               ▼              ▼              ▼              ▼
  /registro        /nosotros      /contacto     /categories    /location
        │
        ▼
   POST /login
        │
        ├────────────── Credenciales válidas ──────────────┐
        │                                                  │
        ▼                                                  ▼
 /productos (autenticado)                         /?error=true
        │
        ├──────────────────────────────┐
        │                              │
        ▼                              ▼
     /cart                   /productos/inventario
        │                              │
        ▼                              ▼
   Checkout                  Administración de inventario
```

---

## Flujos implementados

### 1. Inicio de sesión

```text
HOME
   │
   ▼
POST /login
   │
   ├── Credenciales válidas
   │        ▼
   │   /productos
   │
   └── Credenciales inválidas
            ▼
      /?error=true
```

El acceso a los módulos administrativos se encuentra protegido mediante Spring Security. Los usuarios autenticados pueden acceder a la gestión de productos, inventario y carrito de compras.

---

### 2. Gestión de productos

```text
/productos
      │
      ├── Nuevo producto
      │         │
      │         ▼
      │  POST /productos/guardar
      │         │
      │         ▼
      │    /productos
      │
      ├── Editar producto
      │         │
      │         ▼
      │  POST /productos/guardar
      │         │
      │         ▼
      │    /productos
      │
      └── Eliminar producto
                │
                ▼
           /productos
```

El módulo de productos permite registrar, modificar y eliminar productos utilizando las categorías y proveedores almacenados en la base de datos.

---

### 3. Gestión del carrito

```text
/productos
      │
      ▼
Agregar producto
      │
      ▼
/cart
      │
      ├── Aumentar cantidad
      ├── Disminuir cantidad
      ├── Eliminar producto
      │
      ▼
Continuar al checkout
```

El sistema valida automáticamente el stock disponible antes de agregar o modificar las cantidades de los productos.

---

### 4. Flujo de compra

```text
Inicio
   │
   ▼
Registro o inicio de sesión
   │
   ▼
Listado de productos
   │
   ▼
Agregar productos al carrito
   │
   ▼
Visualizar carrito
   │
   ▼
Checkout
   │
   ├── Método de pago
   ├── Método de envío
   └── Sucursal
   │
   ▼
Confirmar compra
   │
   ▼
Compra exitosa
   │
   ▼
Actualización del inventario
```

Durante la confirmación de compra el sistema genera la venta, registra el detalle de los productos, actualiza el inventario y muestra la confirmación correspondiente.

---

## Funcionalidades en desarrollo

Las siguientes funcionalidades forman parte de las siguientes iteraciones del proyecto:

- Gestión de promociones y descuentos.
- Reportes administrativos.
- Gestión de ganancias y pérdidas.
- Auditoría de cambios.
- Gestión de opiniones de usuarios.
- Soporte técnico.

---

## Seguridad

Las siguientes rutas requieren autenticación mediante Spring Security:

- `/productos/**`
- `/cart/**`
- `/productos/inventario`

Las vistas públicas del sistema permanecen disponibles para cualquier visitante:

- `/`
- `/registro`
- `/nosotros`
- `/contacto`
- `/categories`
- `/location`