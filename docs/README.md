# 📚 Documentación del Proyecto HomeStore

HomeStore es un sistema web para la gestión de productos, inventario y ventas. Esta carpeta reúne la documentación generada durante el curso y diferencia entre lo ya implementado y el modelo funcional que se completará en los siguientes avances.

## 🗂️ Índice general

| Carpeta | Contenido | Avance |
| --- | --- | --- |
| [`01-historias-usuario/`](./01-historias-usuario/) | Historias de usuario en formato Como/Quiero/Para | Avance 1 |
| [`02-prototipo/`](./02-prototipo/) | Mockups y wireframes de las pantallas principales | Avance 1 |
| [`03-diagrama-er/`](./03-diagrama-er/) | Diagrama Entidad-Relación de la base de datos | Avance 1 |
| [`04-arquitectura/`](./04-arquitectura/) | Arquitectura por capas y patrones aplicados | Avance 2 |
| [`05-manual-usuario/`](./05-manual-usuario/) | Cómo usar el sistema (perspectiva del cliente) | Avance 4 |
| [`06-manual-tecnico/`](./06-manual-tecnico/) | Instalación, configuración, deployment, API REST | Avance 4 |
| [`07-informe-ieee/`](./07-informe-ieee/) | Artículo científico en formato IEEE | Avance 3 |
| [`08-presentacion/`](./08-presentacion/) | Slides de la defensa final | Avance 4 |

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

## ✍️ Convenciones

- La documentación se mantiene en **Markdown** para su consulta en GitHub.
- Las imágenes y diagramas se guardan en la carpeta del avance al que pertenecen (excepto los logos).
- Las tablas y columnas de la base de datos se nombran en `snake_case`; las claves primarias terminan en `_id`.
- Los scripts de estructura y los datos de prueba se mantienen separados en [`database/`](../database/).
- Los PDFs (informe IEEE, presentación) se incluyen pero también su fuente editable si se puede.
