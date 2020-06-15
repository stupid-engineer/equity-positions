package com.interview.equityPositions.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UpdateTransactionVo {

	@NotNull(message = "transactionID cannot be empty")
	private Integer transactionID;

	@Pattern(regexp = "(REL|ITC|INF)?", message = "securityCode = REL or ITC or INF")
	private String securityCode;

	private Integer quantity;

	@Pattern(regexp = "(Buy|Sell)?", message = "buyOrSell Buy or Sell")
	private String buyOrSell;
}