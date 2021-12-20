package com.upgrad.PaymentService.services;

import com.upgrad.PaymentService.dto.PaymentRequestDTO;
import com.upgrad.PaymentService.dto.TransactionDetailsDTO;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {

    int performingTransaction(PaymentRequestDTO paymentRequestDTO);

    TransactionDetailsDTO getTransactionDetails(int TransactionId);
}
