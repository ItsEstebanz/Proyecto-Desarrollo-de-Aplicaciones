# 🔀 Flujo de Navegación

Diagrama de cómo se conectan las pantallas del sistema.

```
                        ┌─────────────┐
                        │   HOME (/)  │
                        │  publica    │
                        └──────┬──────┘
                               │
                ┌──────────────┼──────────────┐
                ▼              ▼              ▼
         ┌──────────┐   ┌──────────┐   ┌──────────────┐
         │  /login  │   │/registro │   │ /servicios   │
         └─────┬────┘   └─────┬────┘   │   publica    │
               │              │        └──────────────┘
               └──────┬───────┘
                      ▼
            ┌─────────────────────┐
            │   /dashboard         │
            │  (segun rol)         │
            └─────────┬────────────┘
                      │
        ┌─────────────┼─────────────┬─────────────┐
        ▼             ▼             ▼             ▼
   ┌─────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐
   │/mascotas│  │ /citas   │  │/servicios│  │ /admin/* │
   │         │  │transacc. │  │          │  │(ADMIN)   │
   └────┬────┘  └────┬─────┘  └──────────┘  └──────────┘
        │            │
        ▼            ▼
   ┌────────┐  ┌──────────┐
   │CRUD    │  │/citas/   │
   │mascotas│  │ nueva    │
   └────────┘  └────┬─────┘
                    ▼
              ┌──────────────┐
              │ /citas/{id}  │
              │ + factura    │
              └──────────────┘
```

## Casos de uso principales

### 1. Cliente nuevo agenda una cita

```
HOME → /registro → (auto-login) → /dashboard →
  /mascotas → /mascotas/nueva → (registro mascota) →
  /citas/nueva → (seleccionar mascota, servicio, fecha) →
  CONFIRMACIÓN
```

### 2. Veterinario atiende una cita

```
HOME → /login → /dashboard (vet) →
  /citas → (cita del día) → /citas/{id} →
  (registrar servicios atendidos) → COMPLETAR →
  factura generada automáticamente
```

### 3. Admin genera reporte

```
HOME → /login (admin) → /dashboard →
  /admin/reportes → (filtros) →
  reporte mensual
```

## Estados de una cita

```
   ┌────────────┐
   │ PENDIENTE  │  ← se crea
   └─────┬──────┘
         │
   ┌─────▼──────┐
   │ CONFIRMADA │  ← recepcionista valida
   └─────┬──────┘
         │
   ┌─────▼──────┐
   │ COMPLETADA │  ← veterinario termina + factura
   └────────────┘

   ┌────────────┐
   │ CANCELADA  │  ← cliente o recepcionista
   └────────────┘
```
