# 🏛️ Arquitectura del Sistema

Cómo está organizado el código y por qué.

## 📄 Archivos en esta carpeta

| Archivo | Contenido |
| --- | --- |
| `arquitectura.md` | Capas, patrones aplicados |
| `tecnologias.md` | Stack tecnológico con justificación |
| `diagrama-componentes.png` | Diagrama visual *(placeholder)* |

## 🎯 Resumen

El proyecto sigue el patrón **MVC con 4 capas** que vimos en clase:

```text
┌─────────────────────────────────┐
│      Controller (Web)           │  ← recibe HTTP, llama al Service
├─────────────────────────────────┤
│      Service (lógica)           │  ← aplica reglas de negocio
├─────────────────────────────────┤
│      Repository (datos)         │  ← habla con MySQL
├─────────────────────────────────┤
│      Entity (modelo)            │  ← representa una tabla
├─────────────────────────────────┤
│      MySQL (persistencia)       │
└─────────────────────────────────┘
```

## 📐 Patrones aplicados

- **MVC** — separación de Model, View, Controller.
- **Repository** — abstracción del acceso a datos (Spring Data JPA).
- **Service Layer** — lógica de negocio centralizada.
- **Dependency Injection** — Spring inyecta las dependencias con `@Autowired`.
- **DTO** — Data Transfer Objects para la API REST.

Ver detalle en `arquitectura.md`.
