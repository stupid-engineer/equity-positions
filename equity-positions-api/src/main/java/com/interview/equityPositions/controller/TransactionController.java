package com.interview.equityPositions.controller;

import com.interview.equityPositions.utils.Result;
import com.interview.equityPositions.model.Position;
import com.interview.equityPositions.model.Transaction;
import com.interview.equityPositions.service.TransactionService;
import com.interview.equityPositions.vo.CancelTransactionVo;
import com.interview.equityPositions.vo.CreateTransactionVo;
import com.interview.equityPositions.vo.UpdateTransactionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/list")
    public Result listTransaction(){
        List<Transaction> res = transactionService.listTransaction();
        return Result.ok(res);
    }

    @PostMapping("/create")
    public Result createTransaction(@Valid @RequestBody CreateTransactionVo vo){
        List<Position> res = transactionService.createTransaction(vo);
        return Result.ok(res);
    }

    @PostMapping("/update")
    public Result updateTransaction(@Valid @RequestBody UpdateTransactionVo vo){
        List<Position> res = transactionService.updateTransaction(vo);
        return Result.ok(res);
    }

    @PostMapping("/cancel")
    public Result cancelTransaction(@Valid @RequestBody CancelTransactionVo vo){
        List<Position> res = transactionService.cancelTransaction(vo);
        return Result.ok(res);
    }

}
