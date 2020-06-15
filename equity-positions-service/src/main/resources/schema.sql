CREATE TABLE IF NOT EXISTS t_transaction (
 TransactionID int PRIMARY KEY AUTO_INCREMENT,
 TradeID int not null,
 Version int not null,
 SecurityCode varchar(200) not null,
 Quantity int not null,
 TradeType varchar(20) not null,
 BuyOrSell varchar(20) null
);