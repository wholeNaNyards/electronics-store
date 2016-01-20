DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS UserProducts;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS ProductCategories;
DROP TABLE IF EXISTS Images;

CREATE TABLE Users (
	id IDENTITY,
	firstName VARCHAR(30) NOT NULL,
	lastName VARCHAR(30) NOT NULL,
	username VARCHAR(16) NOT NULL
);

CREATE TABLE Images (
	id IDENTITY,
	name VARCHAR(30) NOT NULL
);

CREATE TABLE Products (
	id IDENTITY,
	name VARCHAR(30) NOT NULL,
	description VARCHAR(2000) NOT NULL,
	price DECIMAL(20, 2) NOT NULL,
	rating INTEGER NOT NULL,
	imageId INTEGER NOT NULL,
	FOREIGN KEY (imageId) REFERENCES Images(id)
);

CREATE TABLE UserProducts (
	userId INTEGER NOT NULL,
	productId INTEGER NOT NULL,
	FOREIGN KEY (userId) REFERENCES Users(id),
	FOREIGN KEY (productId) REFERENCES Products(id),
	PRIMARY KEY (userId, productId)
);

CREATE TABLE Categories (
	id IDENTITY,
	name VARCHAR(30) NOT NULL
);

CREATE TABLE ProductCategories (
	productId INTEGER NOT NULL,
	categoryId INTEGER NOT NULL,
	FOREIGN KEY (productId) REFERENCES Products(id),
	FOREIGN KEY (categoryId) REFERENCES Categories(id),
	PRIMARY KEY (productId, categoryId)
);
