package com.interview.equityPositions.service;

import com.interview.equityPositions.TestApplication;
import com.interview.equityPositions.model.Position;
import com.interview.equityPositions.model.Transaction;
import com.interview.equityPositions.vo.CancelTransactionVo;
import com.interview.equityPositions.vo.CreateTransactionVo;
import com.interview.equityPositions.vo.UpdateTransactionVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest(classes = TestApplication.class)
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    public void listTransaction() {
        List<Transaction> list= transactionService.listTransaction();
        assertThat(list.size(),greaterThan(0));
    }

    @Test
    public void createTransaction() {
        CreateTransactionVo vo = new CreateTransactionVo();
        vo.setTradeID(6);
        vo.setSecurityCode("REL");
        vo.setQuantity(60);
        vo.setBuyOrSell("Buy");
        List<Position> list= transactionService.createTransaction(vo);
        assertThat(list.size(),greaterThan(0));
    }

    @Test
    public void updateTransaction() {
        UpdateTransactionVo vo = new UpdateTransactionVo();
        vo.setTransactionID(4);
        vo.setSecurityCode("REL");
        vo.setQuantity(120);
        vo.setBuyOrSell("Buy");
        List<Position> list= transactionService.updateTransaction(vo);
        assertThat(list.size(),greaterThan(0));
    }

    @Test
    public void cancelTransaction() {
        CancelTransactionVo vo = new CancelTransactionVo();
        vo.setTransactionID(6);
        List<Position> list= transactionService.cancelTransaction(vo);
        assertThat(list.size(),greaterThan(0));
    }
}