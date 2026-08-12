package com.ufide.homestore.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufide.homestore.entity.Cart;
import com.ufide.homestore.entity.CartItem;
import com.ufide.homestore.entity.Product;
import com.ufide.homestore.entity.User;
import com.ufide.homestore.repository.CartItemRepository;
import com.ufide.homestore.repository.CartRepository;
import com.ufide.homestore.repository.ProductRepository;
import com.ufide.homestore.repository.UserRepository;

@Service
public class CartService {

    private static final String ESTADO_ACTIVO = "ACTIVE";

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            ProductRepository productRepository,
            UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<CartItem> listarItems(String correo) {
        User usuario = buscarUsuario(correo);

        return cartRepository.findFirstByUserAndStatusOrderByCreatedAtDesc(
                usuario,
                ESTADO_ACTIVO).map(cartItemRepository::findByCartOrderByCartItemIdAsc).orElseGet(List::of);
    }

    public BigDecimal calcularTotal(List<CartItem> items) {
        return items.stream().map(CartItem::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Transactional
    public void agregarProducto(String correo, Integer productId, Integer cantidad) {
        if (cantidad == null || cantidad < 1) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor a cero");
        }

        User usuario = buscarUsuario(correo);
        Product producto = buscarProducto(productId);

        validarStock(producto, cantidad);

        Cart carrito = obtenerOCrearCarrito(usuario);

        CartItem item = cartItemRepository.findByCartAndProduct(carrito, producto).orElse(null);

        if (item == null) {
            item = new CartItem(
                    carrito,
                    producto,
                    cantidad,
                    producto.getPrice());
        } else {
            int nuevaCantidad = item.getQuantity() + cantidad;

            validarStock(producto, nuevaCantidad);
            item.setQuantity(nuevaCantidad);
        }

        cartItemRepository.save(item);
    }

    @Transactional
    public void aumentarCantidad(String correo, Integer itemId) {
        CartItem item = buscarItemDelUsuario(correo, itemId);
        int nuevaCantidad = item.getQuantity() + 1;

        validarStock(item.getProduct(), nuevaCantidad);

        item.setQuantity(nuevaCantidad);
        cartItemRepository.save(item);
    }

    @Transactional
    public void disminuirCantidad(String correo, Integer itemId) {
        CartItem item = buscarItemDelUsuario(correo, itemId);

        if (item.getQuantity() <= 1) {
            cartItemRepository.delete(item);
            return;
        }

        item.setQuantity(item.getQuantity() - 1);
        cartItemRepository.save(item);
    }

    @Transactional
    public void eliminarItem(String correo, Integer itemId) {
        CartItem item = buscarItemDelUsuario(correo, itemId);
        cartItemRepository.delete(item);
    }

    private Cart obtenerOCrearCarrito(User usuario) {
        return cartRepository.findFirstByUserAndStatusOrderByCreatedAtDesc(
                usuario,
                ESTADO_ACTIVO).orElseGet(() -> cartRepository.save(new Cart(usuario)));
    }

    private CartItem buscarItemDelUsuario(String correo, Integer itemId) {
        User usuario = buscarUsuario(correo);

        CartItem item = cartItemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException(
                "El producto no está en el carrito"));

        boolean perteneceAlUsuario = item.getCart().getUser().getUserId().equals(usuario.getUserId());

        boolean carritoActivo = ESTADO_ACTIVO.equals(
                item.getCart().getStatus());

        if (!perteneceAlUsuario || !carritoActivo) {
            throw new IllegalArgumentException(
                    "No se puede modificar este carrito");
        }

        return item;
    }

    private User buscarUsuario(String correo) {
        return userRepository.findByEmail(correo).orElseThrow(() -> new IllegalArgumentException(
                "Usuario no encontrado"));
    }

    private Product buscarProducto(Integer productId) {
        return productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException(
                "Producto no encontrado"));
    }

    private void validarStock(Product producto, int cantidad) {
        int stockDisponible = producto.getStock() == null
                ? 0
                : producto.getStock();

        if (cantidad > stockDisponible) {
            throw new IllegalArgumentException(
                    "No hay suficientes unidades de " + producto.getName());
        }
    }

    @Transactional(readOnly = true)
    public Cart obtenerCarritoActivo(User usuario) {
        return cartRepository
                .findFirstByUserAndStatusOrderByCreatedAtDesc(
                        usuario,
                        ESTADO_ACTIVO)
                .orElseThrow(() -> new IllegalStateException(
                        "No existe un carrito activo."));
    }

}