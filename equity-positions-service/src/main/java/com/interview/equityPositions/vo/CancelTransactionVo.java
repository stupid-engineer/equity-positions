package com.interview.equityPositions.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CancelTransactionVo {

    @NotNull(message = "TransactionID cannot be empty")
    private Integer transactionID;
}
