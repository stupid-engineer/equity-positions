package com.interview.equityPositions.model;

import lombok.Data;

@Data
public class Transaction {
    private Integer transactionID;
    private Integer tradeID;
    private Integer version;
    private String securityCode;
    private Integer quantity;
    private String tradeType;
    private String buyOrSell;
}
