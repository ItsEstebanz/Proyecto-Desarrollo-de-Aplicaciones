package com.ufide.homestore.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufide.homestore.entity.Cart;
import com.ufide.homestore.entity.CartItem;
import com.ufide.homestore.entity.PaymentMethod;
import com.ufide.homestore.entity.Product;
import com.ufide.homestore.entity.Sale;
import com.ufide.homestore.entity.SaleDetail;
import com.ufide.homestore.entity.ShippingMethod;
import com.ufide.homestore.entity.StoreLocation;
import com.ufide.homestore.entity.User;
import com.ufide.homestore.repository.CartRepository;
import com.ufide.homestore.repository.PaymentMethodRepository;
import com.ufide.homestore.repository.ProductRepository;
import com.ufide.homestore.repository.SaleDetailRepository;
import com.ufide.homestore.repository.SaleRepository;
import com.ufide.homestore.repository.ShippingMethodRepository;
import com.ufide.homestore.repository.StoreLocationRepository;
import com.ufide.homestore.repository.UserRepository;

@Service
public class CheckoutService {

    private final CartService cartService;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final ShippingMethodRepository shippingMethodRepository;
    private final StoreLocationRepository storeLocationRepository;
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

    public CheckoutService(
            CartService cartService,
            CartRepository cartRepository,
            UserRepository userRepository,
            ProductRepository productRepository,
            PaymentMethodRepository paymentMethodRepository,
            ShippingMethodRepository shippingMethodRepository,
            StoreLocationRepository storeLocationRepository,
            SaleRepository saleRepository,
            SaleDetailRepository saleDetailRepository) {

        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.shippingMethodRepository = shippingMethodRepository;
        this.storeLocationRepository = storeLocationRepository;
        this.saleRepository = saleRepository;
        this.saleDetailRepository = saleDetailRepository;
    }

    @Transactional
    public Sale confirmarCompra(
            String correoUsuario,
            Integer paymentMethodId,
            Integer shippingMethodId,
            Integer locationId) {

        User usuario = userRepository.findByEmail(correoUsuario)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró el usuario autenticado."
                ));

        Cart carrito = cartService.obtenerCarritoActivo(usuario);

        if (carrito.getItems() == null || carrito.getItems().isEmpty()) {
            throw new IllegalStateException(
                    "No puedes confirmar una compra con el carrito vacío."
            );
        }

        PaymentMethod metodoPago = paymentMethodRepository
                .findById(paymentMethodId)
                .filter(metodo -> Boolean.TRUE.equals(metodo.getIsActive()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "El método de pago seleccionado no es válido."
                ));

        ShippingMethod metodoEnvio = shippingMethodRepository
                .findById(shippingMethodId)
                .filter(metodo -> Boolean.TRUE.equals(metodo.getIsActive()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "El método de envío seleccionado no es válido."
                ));

        StoreLocation sucursal = storeLocationRepository
                .findById(locationId)
                .filter(location -> Boolean.TRUE.equals(location.getIsActive()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "La sucursal seleccionada no es válida."
                ));

        BigDecimal subtotalProductos = BigDecimal.ZERO;

        for (CartItem item : carrito.getItems()) {

            Product producto = productRepository
                    .findById(item.getProduct().getProductId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Uno de los productos ya no existe."
                    ));

            if (producto.getStock() < item.getQuantity()) {
                throw new IllegalStateException(
                        "No hay suficiente stock para el producto: "
                                + producto.getName()
                );
            }

            subtotalProductos = subtotalProductos.add(
                    item.getUnitPrice().multiply(
                            BigDecimal.valueOf(item.getQuantity())
                    )
            );
        }

        BigDecimal costoEnvio = metodoEnvio.getCost() == null
                ? BigDecimal.ZERO
                : metodoEnvio.getCost();

        BigDecimal totalCompra = subtotalProductos.add(costoEnvio);

        Sale venta = new Sale();
        venta.setInvoiceNumber(generarNumeroFactura());
        venta.setSaleDate(LocalDateTime.now());
        venta.setTotal(totalCompra);
        venta.setStatus("COMPLETED");
        venta.setUser(usuario);
        venta.setPaymentMethod(metodoPago);
        venta.setShippingMethod(metodoEnvio);
        venta.setLocation(sucursal);

        Sale ventaGuardada = saleRepository.save(venta);

        for (CartItem item : carrito.getItems()) {

            Product producto = productRepository
                    .findById(item.getProduct().getProductId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "No se encontró el producto."
                    ));

            SaleDetail detalle = new SaleDetail();
            detalle.setSale(ventaGuardada);
            detalle.setProduct(producto);
            detalle.setQuantity(item.getQuantity());
            detalle.setUnitPrice(item.getUnitPrice());
            detalle.setSubtotal(
                    item.getUnitPrice().multiply(
                            BigDecimal.valueOf(item.getQuantity())
                    )
            );

            saleDetailRepository.save(detalle);

            producto.setStock(
                    producto.getStock() - item.getQuantity()
            );

            productRepository.save(producto);
        }

        carrito.setStatus("COMPLETED");
        cartRepository.save(carrito);

        return ventaGuardada;
    }

    private String generarNumeroFactura() {
        return "HS-"
                + LocalDateTime.now().getYear()
                + "-"
                + UUID.randomUUID()
                    .toString()
                    .substring(0, 8)
                    .toUpperCase();
    }
}