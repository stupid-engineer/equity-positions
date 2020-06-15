package com.interview.equityPositions.dao;

import com.interview.equityPositions.model.Position;
import com.interview.equityPositions.model.Transaction;

import java.util.List;

public interface TransactionDao {
    List<Transaction> queryAll();

    List<Position> queryPosition();

    int insert(Transaction transaction);

    int update(Transaction transaction);

    Transaction queryByTransactionID(int TransactionID);
}
