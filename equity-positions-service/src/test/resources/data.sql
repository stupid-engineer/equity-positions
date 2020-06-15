delete from t_transaction;
INSERT INTO t_transaction (TradeID,Version,SecurityCode,Quantity,TradeType,BuyOrSell) VALUES (1,1,'REL',50,'INSERT','Buy');
INSERT INTO t_transaction (TradeID,Version,SecurityCode,Quantity,TradeType,BuyOrSell) VALUES (2,1,'ITC',40,'INSERT','Sell');
INSERT INTO t_transaction (TradeID,Version,SecurityCode,Quantity,TradeType,BuyOrSell) VALUES (3,1,'INF',70,'INSERT','Buy');
INSERT INTO t_transaction (TradeID,Version,SecurityCode,Quantity,TradeType,BuyOrSell) VALUES (1,2,'REL',60,'UPDATE','Buy');
INSERT INTO t_transaction (TradeID,Version,SecurityCode,Quantity,TradeType,BuyOrSell) VALUES (2,2,'ITC',30,'CANCEL','Buy');
INSERT INTO t_transaction (TradeID,Version,SecurityCode,Quantity,TradeType,BuyOrSell) VALUES (4,1,'INF',20,'INSERT','Sell');
