DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Items;
DROP TABLE IF EXISTS UserItems;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS ItemCategories;
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

CREATE TABLE Items (
	id IDENTITY,
	name VARCHAR(30) NOT NULL,
	description VARCHAR(2000) NOT NULL,
	price DECIMAL(20, 2) NOT NULL,
	rating INTEGER NOT NULL,
	imageId INTEGER NOT NULL,
	FOREIGN KEY (imageId) REFERENCES Images(id)
);

CREATE TABLE UserItems (
	userId INTEGER NOT NULL,
	itemId INTEGER NOT NULL,
	quantity INTEGER NOT NULL,
	FOREIGN KEY (userId) REFERENCES Users(id),
	FOREIGN KEY (itemId) REFERENCES Items(id),
	PRIMARY KEY (userId, itemId)
);

CREATE TABLE Categories (
	id IDENTITY,
	name VARCHAR(30) NOT NULL
);

CREATE TABLE ItemCategories (
	itemId INTEGER NOT NULL,
	categoryId INTEGER NOT NULL,
	FOREIGN KEY (itemId) REFERENCES Items(id),
	FOREIGN KEY (categoryId) REFERENCES Categories(id),
	PRIMARY KEY (itemId, categoryId)
);
