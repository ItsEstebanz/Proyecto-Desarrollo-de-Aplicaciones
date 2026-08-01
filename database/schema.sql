-- ======================================================================
-- homestoredb - Estructura de base de datos
-- Proyecto Final SC-403 - Universidad Fidélitas
-- DDL: Database Initialization
-- MySQL 8.0+
-- ======================================================================
CREATE DATABASE IF NOT EXISTS homestoredb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE homestoredb;

-- ======================================================================
-- Catalogos y seguridad
-- ======================================================================
CREATE TABLE IF NOT EXISTS role (
    role_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (role_id),
    CONSTRAINT uq_role_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS category (
    category_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    CONSTRAINT pk_category PRIMARY KEY (category_id)
);

CREATE TABLE IF NOT EXISTS supplier (
    supplier_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(150),
    CONSTRAINT pk_supplier PRIMARY KEY (supplier_id)
);

CREATE TABLE IF NOT EXISTS `user` (
    user_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INT NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT pk_user PRIMARY KEY (user_id),
    CONSTRAINT uq_user_email UNIQUE (email),
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES role (role_id)
);

CREATE TABLE IF NOT EXISTS product (
    product_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    cost_price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    category_id INT NOT NULL,
    supplier_id INT NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (product_id),
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category (category_id),
    CONSTRAINT fk_product_supplier FOREIGN KEY (supplier_id) REFERENCES supplier (supplier_id)
);

-- ======================================================================
-- Carrito
-- ======================================================================
CREATE TABLE IF NOT EXISTS cart (
    cart_id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    CONSTRAINT pk_cart PRIMARY KEY (cart_id),
    CONSTRAINT fk_cart_user FOREIGN KEY (user_id) REFERENCES `user` (user_id)
);

CREATE TABLE IF NOT EXISTS cart_item (
    cart_item_id INT NOT NULL AUTO_INCREMENT,
    cart_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    CONSTRAINT pk_cart_item PRIMARY KEY (cart_item_id),
    CONSTRAINT uq_cart_item_product UNIQUE (cart_id, product_id),
    CONSTRAINT fk_cart_item_cart FOREIGN KEY (cart_id) REFERENCES cart (cart_id) ON DELETE CASCADE,
    CONSTRAINT fk_cart_item_product FOREIGN KEY (product_id) REFERENCES product (product_id)
);

-- ======================================================================
-- Promociones y descuentos
-- ======================================================================
CREATE TABLE IF NOT EXISTS discount_code (
    code_id INT NOT NULL AUTO_INCREMENT,
    code VARCHAR(50) NOT NULL,
    discount_pct DECIMAL(5, 2) NOT NULL,
    user_id INT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT pk_discount_code PRIMARY KEY (code_id),
    CONSTRAINT uq_discount_code UNIQUE (code),
    CONSTRAINT fk_discount_code_user FOREIGN KEY (user_id) REFERENCES `user` (user_id)
);

CREATE TABLE IF NOT EXISTS promotion (
    promotion_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    discount_type VARCHAR(50) NOT NULL,
    discount_value DECIMAL(10, 2) NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_by INT NOT NULL,
    CONSTRAINT pk_promotion PRIMARY KEY (promotion_id),
    CONSTRAINT fk_promotion_user FOREIGN KEY (created_by) REFERENCES `user` (user_id)
);

CREATE TABLE IF NOT EXISTS product_promotion (
    pp_id INT NOT NULL AUTO_INCREMENT,
    product_id INT NOT NULL,
    promotion_id INT NOT NULL,
    CONSTRAINT pk_product_promotion PRIMARY KEY (pp_id),
    CONSTRAINT uq_product_promotion UNIQUE (product_id, promotion_id),
    CONSTRAINT fk_pp_product FOREIGN KEY (product_id) REFERENCES product (product_id),
    CONSTRAINT fk_pp_promotion FOREIGN KEY (promotion_id) REFERENCES promotion (promotion_id)
);

-- ======================================================================
-- Inventario
-- ======================================================================
CREATE TABLE IF NOT EXISTS inventory_movement (
    movement_id INT NOT NULL AUTO_INCREMENT,
    product_id INT NOT NULL,
    movement_type VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    movement_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id INT NOT NULL,
    CONSTRAINT pk_inventory_movement PRIMARY KEY (movement_id),
    CONSTRAINT fk_inventory_product FOREIGN KEY (product_id) REFERENCES product (product_id),
    CONSTRAINT fk_inventory_user FOREIGN KEY (user_id) REFERENCES `user` (user_id)
);

-- ======================================================================
-- Metodos y ubicaciones
-- ======================================================================
CREATE TABLE IF NOT EXISTS store_location (
    location_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    maps_url VARCHAR(255),
    phone VARCHAR(20),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT pk_store_location PRIMARY KEY (location_id)
);

CREATE TABLE IF NOT EXISTS payment_method (
    method_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT pk_payment_method PRIMARY KEY (method_id)
);

CREATE TABLE IF NOT EXISTS shipping_method (
    method_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    cost DECIMAL(10, 2) NOT NULL DEFAULT 0,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT pk_shipping_method PRIMARY KEY (method_id)
);

-- ======================================================================
-- Ventas
-- ======================================================================
CREATE TABLE IF NOT EXISTS sale (
    sale_id INT NOT NULL AUTO_INCREMENT,
    invoice_number VARCHAR(100) NOT NULL,
    sale_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(12, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    user_id INT NOT NULL,
    payment_method_id INT NOT NULL,
    shipping_method_id INT NOT NULL,
    code_id INT NULL,
    location_id INT NOT NULL,
    CONSTRAINT pk_sale PRIMARY KEY (sale_id),
    CONSTRAINT uq_sale_invoice_number UNIQUE (invoice_number),
    CONSTRAINT fk_sale_user FOREIGN KEY (user_id) REFERENCES `user` (user_id),
    CONSTRAINT fk_sale_payment_method FOREIGN KEY (payment_method_id) REFERENCES payment_method (method_id),
    CONSTRAINT fk_sale_shipping_method FOREIGN KEY (shipping_method_id) REFERENCES shipping_method (method_id),
    CONSTRAINT fk_sale_discount_code FOREIGN KEY (code_id) REFERENCES discount_code (code_id),
    CONSTRAINT fk_sale_location FOREIGN KEY (location_id) REFERENCES store_location (location_id)
);

CREATE TABLE IF NOT EXISTS sale_detail (
    sale_detail_id INT NOT NULL AUTO_INCREMENT,
    sale_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(12, 2) NOT NULL,
    CONSTRAINT pk_sale_detail PRIMARY KEY (sale_detail_id),
    CONSTRAINT fk_sale_detail_sale FOREIGN KEY (sale_id) REFERENCES sale (sale_id),
    CONSTRAINT fk_sale_detail_product FOREIGN KEY (product_id) REFERENCES product (product_id)
);

-- ======================================================================
-- Soporte y resenas
-- ======================================================================
CREATE TABLE IF NOT EXISTS support_ticket (
    ticket_id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    subject VARCHAR(150) NOT NULL,
    description TEXT,
    status VARCHAR(50) NOT NULL DEFAULT 'OPEN',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_support_ticket PRIMARY KEY (ticket_id),
    CONSTRAINT fk_support_ticket_user FOREIGN KEY (user_id) REFERENCES `user` (user_id)
);

CREATE TABLE IF NOT EXISTS review (
    review_id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    sale_id INT NOT NULL,
    rating INT NOT NULL,
    comment TEXT,
    review_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_review PRIMARY KEY (review_id),
    CONSTRAINT fk_review_user FOREIGN KEY (user_id) REFERENCES `user` (user_id),
    CONSTRAINT fk_review_sale FOREIGN KEY (sale_id) REFERENCES sale (sale_id)
);

-- ======================================================================
-- Auditoria
-- ======================================================================
CREATE TABLE IF NOT EXISTS audit_log (
    audit_id INT NOT NULL AUTO_INCREMENT,
    user_id INT NULL,
    action VARCHAR(100) NOT NULL,
    entity_name VARCHAR(100) NOT NULL,
    record_id INT NOT NULL,
    old_value TEXT,
    new_value TEXT,
    action_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_audit_log PRIMARY KEY (audit_id),
    CONSTRAINT fk_audit_log_user FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON DELETE SET NULL
);