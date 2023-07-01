create database ecommerce_springboot_devzxje

use ecommerce_springboot_devzxje


CREATE TABLE roles(
	id			BIGINT	IDENTITY(1,1) PRIMARY KEY,
    role_name		NVARCHAR(50),
)
GO

CREATE TABLE admin(
	id			BIGINT	IDENTITY(1,1) PRIMARY KEY,
    first_name		NVARCHAR(50),
	last_name		NVARCHAR(50),
	username		NVARCHAR(50), 
	admin_password		NVARCHAR(50), 
	admin_image			NVARCHAR(50), 
	id_roles		BIGINT
)
GO

CREATE TABLE category(
	id			BIGINT	IDENTITY(1,1) PRIMARY KEY,
    category_name		NVARCHAR(50),
	category_status		INT, --0: isdeleted, 1: isactived
)
GO

CREATE TABLE product(
	id			BIGINT	IDENTITY(1,1) PRIMARY KEY,
    product_name		NVARCHAR(50),
	product_description		NVARCHAR(max),
	quanity		INT, 
	product_image		NVARCHAR(50), 
	price			FLOAT, 
	id_category		BIGINT,
	product_status		INT, --0: isdeleted, 1: isactived
)
GO

CREATE TABLE customer(
	id			BIGINT	IDENTITY(1,1) PRIMARY KEY,
    first_name		NVARCHAR(50),
	last_name		NVARCHAR(50),
	username        NVARCHAR(50),
	customer_password        NVARCHAR(50),
	customer_image        NVARCHAR(50),
	phone_number        NVARCHAR(15),
	customer_address        NVARCHAR(50),
	id_roles		BIGINT
)
GO

CREATE TABLE shopping_cart(
	id			BIGINT	IDENTITY(1,1) PRIMARY KEY,
    total_item		INT,
	total_price		FLOAT,
	customer_id        BIGINT
)
GO

CREATE TABLE cart_item(
	id			BIGINT	IDENTITY(1,1) PRIMARY KEY,
    quantity		INT,
	total_price		FLOAT,
	shopping_cart_id        BIGINT,
	product_id        BIGINT
)
GO


CREATE TABLE orders (
	id			BIGINT	IDENTITY(1,1) PRIMARY KEY,
    order_date		DATE,
	delivery_date		DATE,
	order_status		INT,
	note        NVARCHAR(max),
	total_price		FLOAT,
	customer_id        BIGINT
)
GO

CREATE TABLE order_detail(
	id			BIGINT	IDENTITY(1,1) PRIMARY KEY,
	quantity		INT,
	total_price		FLOAT,
	product_id      BIGINT,
	order_id		BIGINT
)
GO


/* ---------------------Add relationship between table----------------------------*/

-- product-category
ALTER TABLE product ADD FOREIGN KEY (id_category) REFERENCES category(id)

-- shopping_cart - customer
ALTER TABLE shopping_cart ADD FOREIGN KEY (customer_id) REFERENCES customer(id)

-- cart_item - shopping_cart
ALTER TABLE cart_item ADD FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart(id)

-- cart_item - product
ALTER TABLE cart_item ADD FOREIGN KEY (product_id) REFERENCES product(id)

-- order - customer
ALTER TABLE orders ADD FOREIGN KEY (customer_id) REFERENCES customer(id)

-- order_detail - product 
ALTER TABLE order_detail ADD FOREIGN KEY (product_id) REFERENCES product(id)

-- order_detail - order 
ALTER TABLE order_detail ADD FOREIGN KEY (order_id) REFERENCES orders(id)



/*---------------------------------------------------------------------------*/





