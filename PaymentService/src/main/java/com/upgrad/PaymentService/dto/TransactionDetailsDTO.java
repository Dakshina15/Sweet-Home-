package com.upgrad.PaymentService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailsDTO {
    private int id;
    private int bookingId;
    private String paymentMode;
    private String upiId;
    private String cardNumber;
}
