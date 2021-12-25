
CREATE SCHEMA Market

DROP TABLE IF EXISTS Market.Basket, Market.Store, Market.Product

CREATE TABLE Market.Product
(
	Name VARCHAR(100) NOT NULL,
	Description VARCHAR(255) NOT NULL,
	CONSTRAINT PK_Product PRIMARY KEY (Name)
)

CREATE TABLE Market.Store
(
	Name VARCHAR(100) NOT NULL,
	Price INT NOT NULL,
	Count INT NOT NULL DEFAULT 0,
	CONSTRAINT PK_Store PRIMARY KEY (Name),
	CONSTRAINT FK_Store_ProductId FOREIGN KEY (Name) REFERENCES Market.Product(Name)
)


CREATE TABLE Market.Basket
(
	Name VARCHAR(100) NOT NULL,
	Count INT NOT NULL DEFAULT 0,
	CONSTRAINT PK_Basket PRIMARY KEY (Name),
	CONSTRAINT FK_Basket_ProductId FOREIGN KEY (Name) REFERENCES Market.Product(Name)
)
