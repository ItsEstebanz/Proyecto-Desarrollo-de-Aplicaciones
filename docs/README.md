# 📚 Documentación del Proyecto HomeStore

HomeStore es un sistema web para la gestión de productos, inventario y ventas. Esta carpeta reúne la documentación generada durante el curso y diferencia entre lo ya implementado y el modelo funcional que se completará en los siguientes avances.

## 🗂️ Índice general

| Carpeta | Contenido | Estado actual |
| --- | --- | --- |
| [`01-historias-usuario/`](./01-historias-usuario/) | Historias de usuario y criterios de aceptación | Pendiente de alinear completamente al dominio HomeStore |
| [`02-prototipo/`](./02-prototipo/) | Flujo de navegación y pantallas | Flujo actualizado con las rutas implementadas |
| [`03-diagrama-er/`](./03-diagrama-er/) | Modelo entidad-relación y modelo relacional | Actualizado con el modelo de ventas de 19 tablas |
| [`04-arquitectura/`](./04-arquitectura/) | Arquitectura por capas y tecnologías | Pendiente de actualización |
| [`05-manual-usuario/`](./05-manual-usuario/) | Manual de uso | Pendiente de actualización |
| [`06-manual-tecnico/`](./06-manual-tecnico/) | Instalación, despliegue y API | Pendiente de actualización |
| [`07-informe-ieee/`](./07-informe-ieee/) | Informe en formato IEEE | Pendiente de completar |
| [`08-presentacion/`](./08-presentacion/) | Material para la defensa final | Pendiente de completar |

## 📋 Mapeo con la rúbrica del curso

| Requisito del enunciado | Documento que lo respalda |
| --- | --- |
| Historias de usuario (mín. 20) | `01-historias-usuario/historias-usuario.md` |
| Prototipo visual | `02-prototipo/` |
| Diagrama ER | `03-diagrama-er/diagrama-er.png` |
| Tecnologías utilizadas | `04-arquitectura/tecnologias.md` + README raíz |
| Manual de uso | `05-manual-usuario/manual.md` |
| Instalación y deploy | `06-manual-tecnico/instalacion.md` + `deployment.md` |
| API REST documentada | `06-manual-tecnico/api-rest.md` + `postman/` |
| Informe IEEE | `07-informe-ieee/informe.pdf` |
| Defensa | `08-presentacion/presentacion.pdf` |

## 📋 Estado de la implementación

Actualmente el proyecto cuenta con autenticación basada en usuarios y roles, además del mantenimiento de productos con sus categorías y proveedores. El modelo de datos define también los módulos de carrito, ventas, inventario, promociones, soporte y auditoría; estas tablas forman parte del diseño de la base de datos, aunque sus entidades, vistas y flujos aún se implementarán gradualmente.

La fuente visual del modelo es [`03-diagrama-er/MER_SistemaVentas_v3.5.svg`](./03-diagrama-er/MER_SistemaVentas_v3.5.svg). La descripción de tablas, restricciones y relaciones se mantiene en [`03-diagrama-er/modelo-relacional.md`](./03-diagrama-er/modelo-relacional.md).

## ✍️ Convenciones

- La documentación se mantiene en **Markdown** para su consulta en GitHub.
- Las imágenes y diagramas se guardan en la carpeta del avance al que pertenecen (excepto los logos).
- Las tablas y columnas de la base de datos se nombran en `snake_case`; las claves primarias terminan en `_id`.
- Los scripts de estructura y los datos de prueba se mantienen separados en [`database/`](../database/).
- Los PDFs (informe IEEE, presentación) se incluyen pero también su fuente editable si se puede.
