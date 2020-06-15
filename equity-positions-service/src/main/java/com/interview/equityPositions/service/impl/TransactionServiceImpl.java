package com.interview.equityPositions.service.impl;

import com.interview.equityPositions.dao.impl.TransactionDaoImpl;
import com.interview.equityPositions.exception.BusinessException;
import com.interview.equityPositions.model.Position;
import com.interview.equityPositions.model.Transaction;
import com.interview.equityPositions.service.TransactionService;
import com.interview.equityPositions.vo.CancelTransactionVo;
import com.interview.equityPositions.vo.CreateTransactionVo;
import com.interview.equityPositions.vo.UpdateTransactionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDaoImpl dao;

    @Override
    public List<Transaction> listTransaction() throws BusinessException {
        return dao.queryAll();
    }

    @Override
    public List<Position> createTransaction(CreateTransactionVo vo) throws BusinessException {
        Transaction transaction = new Transaction();
        transaction.setVersion(1);
        transaction.setTradeType("INSERT");
        transaction.setTradeID(vo.getTradeID());
        transaction.setSecurityCode(vo.getSecurityCode());
        transaction.setQuantity(vo.getQuantity());
        transaction.setBuyOrSell(vo.getBuyOrSell());
        if(dao.insert(transaction) != 1){
            throw new BusinessException("create transaction fail");
        }
        return dao.queryPosition();
    }

    @Override
    public List<Position> updateTransaction(UpdateTransactionVo vo) throws BusinessException {
        Transaction transaction = dao.queryByTransactionID(vo.getTransactionID());
        if(transaction == null){
            throw new BusinessException("transaction not found");
        }
        if(transaction.getTradeType().equals("CANCEL")){
            throw new BusinessException("transaction cancel already");
        }
        transaction.setVersion(transaction.getVersion()+1);
        transaction.setTradeType("UPDATE");
        int flag = 0;
        if(vo.getSecurityCode() != null && !transaction.getSecurityCode().equals(vo.getSecurityCode())){
            transaction.setSecurityCode(vo.getSecurityCode());
            flag++;
        }
        if(vo.getQuantity()> 0 && transaction.getQuantity() != vo.getQuantity()){
            transaction.setQuantity(vo.getQuantity());
            flag++;
        }
        if(vo.getBuyOrSell() != null && !transaction.getBuyOrSell().equals(vo.getBuyOrSell())){
            transaction.setBuyOrSell(vo.getBuyOrSell());
            flag++;
        }
        if(flag == 0){
            throw new BusinessException("duplicate update operation");
        }
        if(dao.insert(transaction) != 1){
            throw new BusinessException("update transaction fail");
        }
        return dao.queryPosition();
    }

    @Override
    public List<Position> cancelTransaction(CancelTransactionVo vo) throws BusinessException {
        Transaction transaction = dao.queryByTransactionID(vo.getTransactionID());
        if(transaction == null){
            throw new BusinessException("transaction not found");
        }
        if(transaction.getTradeType().equals("CANCEL")){
            throw new BusinessException("transaction cancel already");
        }
        transaction.setVersion(transaction.getVersion()+1);
        transaction.setTradeType("CANCEL");
        if(dao.insert(transaction) != 1){
            throw new BusinessException("cancel transaction fail");
        }
        return dao.queryPosition();
    }
}
