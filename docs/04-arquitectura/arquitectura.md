# Arquitectura Técnica — VetCare

## Capas del sistema

```
[Cliente (Navegador)] ─HTTP─> [Controller] ─> [Service] ─> [Repository] ─> [MySQL]
                                                                 │
                                                          [Entity Java]
```

### 1. Capa Controller (`controller/`)

Recibe peticiones HTTP, extrae parámetros, llama al Service, devuelve la vista o JSON.

```java
@Controller
@RequestMapping("/mascotas")
public class MascotaController {
    @Autowired private MascotaService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("mascotas", service.listarTodas());
        return "mascotas/lista";
    }
}
```

Responsabilidades:
- Routing de URLs
- Validación de forma de los inputs
- Selección de la vista

NO debe contener: lógica de negocio, queries SQL.

### 2. Capa Service (`service/`)

Lógica de negocio. Coordina llamadas a varios repositories cuando hace falta.

```java
@Service
public class CitaService {
    @Autowired private CitaRepository citaRepo;
    @Autowired private FacturaService facturaService;

    @Transactional
    public Cita completar(Long citaId) {
        Cita c = citaRepo.findById(citaId).orElseThrow();
        c.setEstado(EstadoCita.COMPLETADA);
        facturaService.generarPara(c);  // efecto secundario
        return citaRepo.save(c);
    }
}
```

Responsabilidades:
- Reglas del negocio (estados de cita, cálculos, validaciones)
- Transacciones (`@Transactional`)
- Coordinación entre repositories

### 3. Capa Repository (`repository/`)

Interfaces que extienden `JpaRepository`. Spring genera la implementación.

```java
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByClienteIdAndActivaTrue(Long clienteId);
    List<Mascota> findByNombreContainingIgnoreCase(String nombre);
}
```

Responsabilidades:
- Acceso a datos
- Query methods derivados
- `@Query` para casos complejos

### 4. Capa Entity (`entity/`)

Clases Java que representan las tablas. Anotaciones JPA.

```java
@Entity
@Table(name = "mascota")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    // ...
}
```

## Patrones de diseño aplicados

| Patrón | Dónde se aplica | Por qué |
|---|---|---|
| **MVC** | Toda la app | Separación Model / View / Controller |
| **Repository** | `repository/` | Abstrae el acceso a datos |
| **Service Layer** | `service/` | Centraliza lógica de negocio |
| **Dependency Injection** | `@Autowired` | Bajo acoplamiento, fácil testing |
| **Singleton** | Beans de Spring | Una instancia compartida |
| **Front Controller** | DispatcherServlet | Punto único de entrada HTTP |
| **DTO** | `dto/` (API REST) | No exponer la Entity directamente |
| **Strategy** | Estados de cita | PENDIENTE/CONFIRMADA/COMPLETADA |

## Seguridad

Spring Security configurado con:
- `BCryptPasswordEncoder` para passwords.
- Filtros de URL por rol (`@PreAuthorize`).
- Login form-based con sesión.

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // ...
}
```

## API REST

Endpoints `/api/**` separados de la web tradicional:

| Endpoint | Método | Devuelve |
|---|---|---|
| `/api/mascotas` | GET | Lista JSON de mascotas |
| `/api/citas` | POST | Crea cita, devuelve 201 |

Documentados con Postman (ver `postman/`).

## Internacionalización

Archivos en `src/main/resources/`:
- `messages.properties` — español (default)
- `messages_en.properties` — inglés

Usado en HTML con `th:text="#{clave}"`.
