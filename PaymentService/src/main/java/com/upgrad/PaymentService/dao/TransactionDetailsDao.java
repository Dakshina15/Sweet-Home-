package com.upgrad.PaymentService.dao;

import com.upgrad.PaymentService.entities.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailsDao extends JpaRepository<TransactionDetailsEntity, Integer> {
}
