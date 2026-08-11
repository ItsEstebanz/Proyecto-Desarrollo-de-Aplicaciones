# Arquitectura Técnica — HomeStore

## Capas del sistema

```txt
[Cliente (Navegador)] ─HTTP─> [Controller] ─> [Service] ─> [Repository] ─> [MySQL]
                                                                 │
                                                          [Entity Java]
```

### 1. Capa Controller (`controller/`)

Recibe peticiones HTTP, extrae parámetros, llama al Service, devuelve la vista o JSON.

```java
@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String verCarrito(Authentication authentication, Model model) {

        List<CartItem> items =
                cartService.listarItems(authentication.getName());

        model.addAttribute("items", items);
        model.addAttribute("total", cartService.calcularTotal(items));

        return "cart";
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
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       ProductRepository productRepository,
                       UserRepository userRepository) {

        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void agregarProducto(String correo, Integer productId, Integer cantidad) {

        User usuario = userRepository.findByEmail(correo)
                .orElseThrow();

        Product producto = productRepository.findById(productId)
                .orElseThrow();

        // Aqui se aplican las reglas del carrito.
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
public interface CartRepository
        extends JpaRepository<Cart, Integer> {

    Optional<Cart> findFirstByUserAndStatusOrderByCreatedAtDesc(User user,String status);
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
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String status;

    // Getters y setters
}
```

## Patrones de diseño aplicados

| Patrón | Dónde se aplica | Por qué |
| --- | --- | --- |
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
| --- | --- | --- |
| `/api/mascotas` | GET | Lista JSON de mascotas |
| `/api/citas` | POST | Crea cita, devuelve 201 |

Documentados con Postman (ver `postman/`).

## Internacionalización

Archivos en `src/main/resources/`:

- `messages.properties` — español (default)
- `messages_en.properties` — inglés

Usado en HTML con `th:text="#{clave}"`.

## Capa de entidades

- Sale
- SaleDetail
- PaymentMethod
- ShippingMethod
- StoreLocation
- Cart
- CartItem

## Capa de repositorios

- SaleRepository
- SaleDetailRepository
- PaymentMethodRepository
- ShippingMethodRepository
- StoreLocationRepository

## Capa de servicios

- CartService
- CheckoutService
- UserService

## Capa de controladores

- CheckoutController
- RegistroController
- CartController
- ProductController

## Capa de presentación

- registro.html
- cart.html
- checkout.html
- compra-exitosa.html
- producto-form.html

El sistema utiliza una arquitectura por capas:

1. Las vistas envían solicitudes a los controladores.
2. Los controladores procesan las solicitudes.
3. Los servicios contienen las reglas del negocio.
4. Los repositorios realizan el acceso a MySQL.
5. Las entidades representan las tablas de la base de datos.
