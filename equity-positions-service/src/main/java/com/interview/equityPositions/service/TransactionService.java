package com.interview.equityPositions.service;

import com.interview.equityPositions.exception.BusinessException;
import com.interview.equityPositions.model.Position;
import com.interview.equityPositions.model.Transaction;
import com.interview.equityPositions.vo.CancelTransactionVo;
import com.interview.equityPositions.vo.CreateTransactionVo;
import com.interview.equityPositions.vo.UpdateTransactionVo;

import java.util.List;

public interface TransactionService {

    List<Transaction> listTransaction() throws BusinessException;

    List<Position> createTransaction(CreateTransactionVo vo) throws BusinessException;

    List<Position> updateTransaction(UpdateTransactionVo vo) throws BusinessException;

    List<Position> cancelTransaction(CancelTransactionVo vo) throws BusinessException;
}
