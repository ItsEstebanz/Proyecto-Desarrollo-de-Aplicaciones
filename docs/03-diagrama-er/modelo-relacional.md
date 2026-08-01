# Modelo Relacional - HomeStore

Este documento describe el modelo de ventas representado en [`MER_SistemaVentas_v3.5.svg`](./MER_SistemaVentas_v3.5.svg). El modelo contiene **19 tablas**. Las cinco tablas ya usadas por la aplicación son `role`, `user`, `category`, `supplier` y `product`; las demás completan el diseño de los módulos futuros.

> Los identificadores se definen como `INT` para mantener compatibilidad con las entidades Java actuales, que usan `Integer`. El diagrama no especifica tipos físicos, por lo que los demás tipos se han definido para MySQL 8.

## Tablas implementadas actualmente

### `role`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| role_id | INT | NO | PK | Identificador del rol. |
| name | VARCHAR(50) | NO | UNIQUE | Nombre del rol. |

### `user`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| user_id | INT | NO | PK | Identificador del usuario. |
| name | VARCHAR(100) | NO | | Nombre visible. |
| email | VARCHAR(150) | NO | UNIQUE | Correo para autenticación. |
| password | VARCHAR(255) | NO | | Hash de la contraseña. |
| role_id | INT | NO | FK → role(role_id) | Rol asignado. |
| is_active | BOOLEAN | NO | | Estado de la cuenta. |

### `category`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| category_id | INT | NO | PK | Identificador de categoría. |
| name | VARCHAR(100) | NO | | Nombre de categoría. |
| description | VARCHAR(500) | SÍ | | Descripción opcional. |

### `supplier`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| supplier_id | INT | NO | PK | Identificador del proveedor. |
| name | VARCHAR(100) | NO | | Nombre del proveedor. |
| phone | VARCHAR(20) | SÍ | | Teléfono de contacto. |
| email | VARCHAR(150) | SÍ | | Correo de contacto. |

### `product`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| product_id | INT | NO | PK | Identificador del producto. |
| name | VARCHAR(150) | NO | | Nombre comercial. |
| description | TEXT | SÍ | | Descripción del producto. |
| price | DECIMAL(10,2) | NO | | Precio de venta. |
| cost_price | DECIMAL(10,2) | NO | | Precio de costo. |
| stock | INT | NO | | Existencia disponible. |
| category_id | INT | NO | FK → category(category_id) | Categoría del producto. |
| supplier_id | INT | NO | FK → supplier(supplier_id) | Proveedor principal. |

## Tablas del modelo de ventas

### `cart`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| cart_id | INT | NO | PK | Identificador del carrito. |
| user_id | INT | NO | FK → user(user_id) | Usuario propietario. |
| created_at | DATETIME | NO | | Fecha de creación. |
| status | VARCHAR(50) | NO | | Estado del carrito. |

### `cart_item`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| cart_item_id | INT | NO | PK | Identificador de la línea. |
| cart_id | INT | NO | FK → cart(cart_id) | Carrito asociado. |
| product_id | INT | NO | FK → product(product_id) | Producto agregado. |
| quantity | INT | NO | | Cantidad solicitada. |
| unit_price | DECIMAL(10,2) | NO | | Precio al agregar al carrito. |

### `discount_code`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| code_id | INT | NO | PK | Identificador del código. |
| code | VARCHAR(50) | NO | UNIQUE | Código canjeable. |
| discount_pct | DECIMAL(5,2) | NO | | Porcentaje de descuento. |
| user_id | INT | SÍ | FK → user(user_id) | Usuario destinatario; nulo si es general. |
| is_active | BOOLEAN | NO | | Estado del código. |

### `promotion`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| promotion_id | INT | NO | PK | Identificador de la promoción. |
| name | VARCHAR(100) | NO | | Nombre de la promoción. |
| discount_type | VARCHAR(50) | NO | | Tipo de descuento. |
| discount_value | DECIMAL(10,2) | NO | | Valor del descuento. |
| start_date | DATETIME | NO | | Inicio de la vigencia. |
| end_date | DATETIME | NO | | Fin de la vigencia. |
| is_active | BOOLEAN | NO | | Estado de la promoción. |
| created_by | INT | NO | FK → user(user_id) | Usuario que la creó. |

### `product_promotion`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| pp_id | INT | NO | PK | Identificador de la asociación. |
| product_id | INT | NO | FK → product(product_id) | Producto incluido. |
| promotion_id | INT | NO | FK → promotion(promotion_id) | Promoción aplicada. |

### `inventory_movement`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| movement_id | INT | NO | PK | Identificador del movimiento. |
| product_id | INT | NO | FK → product(product_id) | Producto afectado. |
| movement_type | VARCHAR(50) | NO | | Entrada, salida o ajuste. |
| quantity | INT | NO | | Cantidad del movimiento. |
| movement_date | DATETIME | NO | | Fecha del movimiento. |
| user_id | INT | NO | FK → user(user_id) | Usuario responsable. |

### `store_location`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| location_id | INT | NO | PK | Identificador de la sucursal. |
| name | VARCHAR(100) | NO | | Nombre de la ubicación. |
| address | VARCHAR(255) | NO | | Dirección física. |
| maps_url | VARCHAR(255) | SÍ | | Enlace de ubicación. |
| phone | VARCHAR(20) | SÍ | | Teléfono de contacto. |
| is_active | BOOLEAN | NO | | Estado de la sucursal. |

### `payment_method`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| method_id | INT | NO | PK | Identificador del método. |
| name | VARCHAR(50) | NO | | Nombre del método de pago. |
| description | VARCHAR(255) | SÍ | | Descripción opcional. |
| is_active | BOOLEAN | NO | | Estado del método. |

### `shipping_method`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| method_id | INT | NO | PK | Identificador del método. |
| name | VARCHAR(50) | NO | | Nombre del método de envío. |
| description | VARCHAR(255) | SÍ | | Descripción opcional. |
| cost | DECIMAL(10,2) | NO | | Costo de envío. |
| is_active | BOOLEAN | NO | | Estado del método. |

### `sale`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| sale_id | INT | NO | PK | Identificador de venta. |
| invoice_number | VARCHAR(100) | NO | UNIQUE | Número de factura. |
| sale_date | DATETIME | NO | | Fecha de venta. |
| total | DECIMAL(12,2) | NO | | Total de la venta. |
| status | VARCHAR(50) | NO | | Estado de la venta. |
| user_id | INT | NO | FK → user(user_id) | Cliente que compra. |
| payment_method_id | INT | NO | FK → payment_method(method_id) | Método de pago. |
| shipping_method_id | INT | NO | FK → shipping_method(method_id) | Método de envío. |
| code_id | INT | SÍ | FK → discount_code(code_id) | Código aplicado. |
| location_id | INT | NO | FK → store_location(location_id) | Sucursal asociada. |

### `sale_detail`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| sale_detail_id | INT | NO | PK | Identificador del detalle. |
| sale_id | INT | NO | FK → sale(sale_id) | Venta asociada. |
| product_id | INT | NO | FK → product(product_id) | Producto vendido. |
| quantity | INT | NO | | Cantidad vendida. |
| unit_price | DECIMAL(10,2) | NO | | Precio aplicado. |
| subtotal | DECIMAL(12,2) | NO | | Importe de la línea. |

### `support_ticket`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| ticket_id | INT | NO | PK | Identificador del ticket. |
| user_id | INT | NO | FK → user(user_id) | Usuario que reporta. |
| subject | VARCHAR(150) | NO | | Asunto del ticket. |
| description | TEXT | SÍ | | Descripción detallada. |
| status | VARCHAR(50) | NO | | Estado del ticket. |
| created_at | DATETIME | NO | | Fecha de creación. |

### `review`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| review_id | INT | NO | PK | Identificador de la reseña. |
| user_id | INT | NO | FK → user(user_id) | Usuario que reseña. |
| sale_id | INT | NO | FK → sale(sale_id) | Venta asociada. |
| rating | INT | NO | | Calificación de 1 a 5. |
| comment | TEXT | SÍ | | Comentario opcional. |
| review_date | DATETIME | NO | | Fecha de la reseña. |

### `audit_log`

| Columna | Tipo | Nulo | Clave | Descripción |
| --- | --- | --- | --- | --- |
| audit_id | INT | NO | PK | Identificador de auditoría. |
| user_id | INT | SÍ | FK → user(user_id) | Usuario responsable; nulo para acciones del sistema. |
| action | VARCHAR(100) | NO | | Acción realizada. |
| entity_name | VARCHAR(100) | NO | | Entidad afectada. |
| record_id | INT | NO | | Identificador del registro afectado. |
| old_value | TEXT | SÍ | | Valor previo. |
| new_value | TEXT | SÍ | | Valor posterior. |
| action_date | DATETIME | NO | | Fecha de la acción. |

## Reglas de integridad

- `user.email`, `role.name`, `discount_code.code` y `sale.invoice_number` son únicos.
- `cart_item` impide repetir el mismo producto en un carrito mediante `UNIQUE (cart_id, product_id)`.
- `product_promotion` impide asociar dos veces el mismo producto con la misma promoción.
- Las cantidades de carrito, detalle y movimiento deben ser positivas; las reseñas se limitan de 1 a 5 estrellas.
- Una venta conserva su precio unitario y subtotal en `sale_detail`, aunque el precio actual del producto cambie después.
- Una promoción o código se aplica solo mientras esté activo y dentro de su vigencia; esa validación pertenece a la lógica de negocio.

## Relaciones principales

- `role` 1:N `user`.
- `category` 1:N `product` y `supplier` 1:N `product`.
- `user` 1:N `cart`, `sale`, `support_ticket`, `review`, `promotion`, `inventory_movement` y `audit_log`.
- `cart` 1:N `cart_item`; `product` 1:N `cart_item`.
- `product` N:M `promotion` mediante `product_promotion`.
- `sale` 1:N `sale_detail`; `product` 1:N `sale_detail`.
- `product` 1:N `inventory_movement`.

## Alcance actual

El archivo [`database/schema.sql`](../../database/schema.sql) contiene la estructura completa del modelo. Los datos iniciales se cargan por separado en `database/seed-data.sql`; ese archivo no forma parte de esta definición.
