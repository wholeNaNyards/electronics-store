----------------------------------
------- Users Table Inserts ------
----------------------------------
INSERT INTO Users(firstName, lastName, username) 
	VALUES(
		'Default',
		'User',
		'dUser');

INSERT INTO Users(firstName, lastName, username)
	VALUES(
		'Joe',
		'Somebody',
		'jSomebody');
		
INSERT INTO Users(firstName, lastName, username)
	VALUES(
		'Tim',
		'Smith',
		'tSmith');
		
INSERT INTO Users(firstName, lastName, username)
	VALUES(
		'Nick',
		'Repetti', 
		'nRepetti');

----------------------------------
------ Images Table Inserts ------
----------------------------------
INSERT INTO Images(name)
	VALUES(
		'imageOne.jpg');

INSERT INTO Images(name)
	VALUES(
		'imageTwo.jpg');

INSERT INTO Images(name)
	VALUES(
		'imageThree.jpg');

INSERT INTO Images(name)
	VALUES(
		'imageFour.jpg');

----------------------------------
------ Items Table Inserts -------
----------------------------------
INSERT INTO Items(name, description, price, rating, imageId) 
	VALUES(
		'Zivio 55 Inch LED TV', 
		'A product called one with a very long description about something about the product. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
		150.99,
		4,
		4);

INSERT INTO Items(name, description, price, rating, imageId) 
	VALUES(
		'Massung 42 Inch LED Smart TV', 
		'A product called two with a very long description about something about the product. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
		124.99,
		2,
		4);
		
INSERT INTO Items(name, description, price, rating, imageId) 
	VALUES(
		'GL 30 Inch LED TV', 
		'A product called three with a very long description about something about the product. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
		79.99,
		5,
		4);

----------------------------------
---- UserItems Table Inserts -----
----------------------------------
INSERT INTO UserItems(userId, itemId, quantity) 
	VALUES(
		1,
		1,
		1);
		
INSERT INTO UserItems(userId, itemId, quantity) 
	VALUES(
		1,
		2,
		1);
		
INSERT INTO UserItems(userId, itemId, quantity) 
	VALUES(
		2,
		3,
		2);
		
INSERT INTO UserItems(userId, itemId, quantity) 
	VALUES(
		2,
		1,
		2);
		
INSERT INTO UserItems(userId, itemId, quantity) 
	VALUES(
		3,
		1,
		2);
		
----------------------------------
---- Categories Table Inserts ----
----------------------------------
INSERT INTO Categories(name) 
	VALUES(
		'Televisions');
		
INSERT INTO Categories(name) 
	VALUES(
		'Video Games');
		
INSERT INTO Categories(name) 
	VALUES(
		'Audio');
		
----------------------------------
-- ItemCategories Table Inserts --
----------------------------------
INSERT INTO ItemCategories(itemId, categoryId) 
	VALUES(
		1,
		1);
		
INSERT INTO ItemCategories(itemId, categoryId) 
	VALUES(
		2,
		1);
		
INSERT INTO ItemCategories(itemId, categoryId) 
	VALUES(
		3,
		1);
