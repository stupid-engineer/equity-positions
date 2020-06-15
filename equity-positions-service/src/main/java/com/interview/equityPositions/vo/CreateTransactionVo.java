package com.interview.equityPositions.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class CreateTransactionVo {

    @NotNull(message = "tradeID cannot be empty")
    private Integer tradeID;

    @NotBlank(message = "securityCode cannot be empty")
    @Pattern(regexp = "(REL|ITC|INF)", message = "securityCode = REL or ITC or INF")
    private String securityCode;

    @NotNull(message = "quantity cannot be empty")
    @Min(value = 1, message = "quantity must be greater than 0")
    private Integer quantity;

    @NotBlank(message = "buyOrSell cannot be empty")
    @Pattern(regexp = "(Buy|Sell)", message = "buyOrSell = Buy or Sell")
    private String buyOrSell;
}
