package com.upgrad.PaymentService.controllers;

import com.upgrad.PaymentService.dto.PaymentRequestDTO;
import com.upgrad.PaymentService.dto.TransactionDetailsDTO;
import com.upgrad.PaymentService.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    /**
     * Using POST METHOD - ("/transaction")
     * @param paymentRequestDTO
     * @return
     */
    @PostMapping("/transaction")
    public ResponseEntity<Integer> handleTransaction(@RequestBody PaymentRequestDTO paymentRequestDTO) {

        int transactionId = transactionService.performingTransaction(paymentRequestDTO);
        return new ResponseEntity<>(transactionId, HttpStatus.CREATED);

    }

    /**
     * Using GET METHOD -("/transaction/{transactionId}")
     * @param transactionId
     * @return
     */
    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<TransactionDetailsDTO> getTransactionDetails(@PathVariable(name = "transactionId") int transactionId) {

        TransactionDetailsDTO transactionDetailsDTO = transactionService.getTransactionDetails(transactionId);
        return new ResponseEntity(transactionDetailsDTO, HttpStatus.OK);

    }
}

