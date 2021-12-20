package com.upgrad.PaymentService.services;

import com.upgrad.PaymentService.dao.TransactionDetailsDao;
import com.upgrad.PaymentService.dto.PaymentRequestDTO;
import com.upgrad.PaymentService.dto.TransactionDetailsDTO;
import com.upgrad.PaymentService.entities.TransactionDetailsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionDetailsDao transactionDetailsDao;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public int performingTransaction(PaymentRequestDTO paymentRequestDTO) {

        TransactionDetailsEntity transactionDetailsEntity = TransactionDetailsEntity.builder()
                .bookingId(paymentRequestDTO.getBookingId())
                .upiId(paymentRequestDTO.getUpiId())
                .paymentMode(paymentRequestDTO.getPaymentMode())
                .cardNumber(paymentRequestDTO.getCardNumber())
                .build();

        //Saving in Database
        transactionDetailsDao.save(transactionDetailsEntity);
        //return
        return transactionDetailsEntity.getId();
    }

    @Override
    public TransactionDetailsDTO getTransactionDetails(int transactionId) {
        TransactionDetailsEntity transactionDetailsEntity = transactionDetailsDao.getById(transactionId);
        TransactionDetailsDTO transactionDetailsDTO = modelMapper.map(transactionDetailsEntity, TransactionDetailsDTO.class);
        //return
        return transactionDetailsDTO;
    }
}
