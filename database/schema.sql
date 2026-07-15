-- ================================================================
-- homestoredb - Schema
-- Proyecto Final SC-403 - Universidad Fidelitas
-- ================================================================

-- ----------------------------------------------------------------
-- DLL: Database Initialization
-- ----------------------------------------------------------------

-- BORRAR LA DATABASE, PRUEBAS ONLY
-- DROP DATABASE IF EXISTS homestoredb;

-- Crear database con soperte para unicode, solo 1 vez se necesita, si no es borrada.
CREATE DATABASE homestoredb
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE homestoredb;

-- Tablas mínimas del sprint (role, category, supplier, user, product).

-- Tabla de roles sin dependencias externas.
CREATE TABLE role (
	role_id	INT	NOT NULL AUTO_INCREMENT,
	name	VARCHAR(50)	NOT NULL,
	CONSTRAINT pk_role	PRIMARY KEY (role_id)
);

-- Tabla de categorías sin dependencias externas.
CREATE TABLE category (
	category_id	INT	NOT NULL AUTO_INCREMENT,
	name	VARCHAR(100)	NOT NULL,
	description	VARCHAR(500),
	CONSTRAINT pk_category	PRIMARY KEY (category_id)
);

-- Tabla de proveedores sin dependencias externas.
CREATE TABLE supplier (
	supplier_id	INT	NOT NULL AUTO_INCREMENT,
	name	VARCHAR(100)	NOT NULL,
	phone	VARCHAR(20),
	email	VARCHAR(150),
	CONSTRAINT pk_supplier	PRIMARY KEY (supplier_id)
);

-- Tabla de usuarios (palabra reservada), requiere tabla role.
CREATE TABLE `user` (
	user_id	INT	NOT NULL AUTO_INCREMENT,
	name	VARCHAR(100)	NOT NULL,
	email	VARCHAR(150)	NOT NULL,
	password	VARCHAR(255)	NOT NULL,
	role_id	INT	NOT NULL,
	is_active	BOOLEAN	NOT NULL DEFAULT TRUE,
	CONSTRAINT pk_user	PRIMARY KEY (user_id),
	CONSTRAINT uq_user_email	UNIQUE (email),
	CONSTRAINT fk_user_role	FOREIGN KEY (role_id) REFERENCES role (role_id)
);

-- Tabla de productos, requiere tablas category y supplier.
CREATE TABLE product (
	product_id	INT	NOT NULL AUTO_INCREMENT,
	name	VARCHAR(150)    NOT NULL,
	description	TEXT,
	price	DECIMAL(10, 2)	NOT NULL,
	cost_price	DECIMAL(10, 2)	NOT NULL,
	stock	INT	NOT NULL DEFAULT 0,
	category_id	INT	NOT NULL,
	supplier_id	INT	NOT NULL,
	CONSTRAINT pk_product	PRIMARY KEY (product_id),
	CONSTRAINT fk_product_category	FOREIGN KEY (category_id) REFERENCES category (category_id),
	CONSTRAINT fk_product_supplier	FOREIGN KEY (supplier_id) REFERENCES supplier (supplier_id)
);
