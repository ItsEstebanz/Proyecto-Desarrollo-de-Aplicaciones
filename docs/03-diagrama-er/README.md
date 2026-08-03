# 🗃️ Diagrama Entidad-Relación

El modelo entidad-relación de HomeStore representa la gestión de catálogo, usuarios, ventas, inventario, promociones y soporte al cliente.

## 📄 Archivos

| Archivo | Contenido |
| --- | --- |
| [`MER_SistemaVentas_v3.5.svg`](./MER_SistemaVentas_v3.5.svg) | Diagrama visual fuente del modelo de ventas. |
| [`modelo-relacional.md`](./modelo-relacional.md) | Tablas, relaciones y reglas de integridad. |
| [`../../database/schema.sql`](../../database/schema.sql) | Script MySQL 8 para crear la estructura. |

## 📊 Resumen del modelo

El diagrama contiene **19 tablas**:

| Módulo | Tablas |
| --- | --- |
| Seguridad y catálogo | `role`, `user`, `category`, `supplier`, `product` |
| Carrito y promociones | `cart`, `cart_item`, `discount_code`, `promotion`, `product_promotion` |
| Ventas e inventario | `store_location`, `payment_method`, `shipping_method`, `sale`, `sale_detail`, `inventory_movement` |
| Soporte y trazabilidad | `support_ticket`, `review`, `audit_log` |

Las tablas ya conectadas a la aplicación son `role`, `user`, `category`, `supplier` y `product`. Las demás ya están definidas en el script de esquema para que se implementen en módulos posteriores.

> **Nota:** Esta es la representación visual. [MER_SistemaVentas_v3.5.svg](./MER_SistemaVentas_v3.5.svg)

## 🔗 Relaciones principales

- `role` 1:N `user`.
- `category` 1:N `product` y `supplier` 1:N `product`.
- `user` 1:N `cart`, `sale`, `support_ticket`, `review`, `promotion`, `inventory_movement` y `audit_log`.
- `cart` 1:N `cart_item`; cada `cart_item` referencia un `product`.
- `product` N:M `promotion` mediante `product_promotion`.
- `sale` 1:N `sale_detail`; cada detalle conserva el precio aplicado al momento de la venta.
- `product` 1:N `inventory_movement`.

## ✅ Criterios cubiertos

- ✅ Más de 8 tablas y relaciones 1:N.
- ✅ Relación N:M entre productos y promociones.
- ✅ Tablas transaccionales: `cart`, `sale`, `sale_detail` e `inventory_movement`.
- ✅ Llaves primarias, foráneas, restricciones únicas y validaciones básicas de datos.
- ✅ Auditoría y soporte ligados al usuario cuando corresponde.
