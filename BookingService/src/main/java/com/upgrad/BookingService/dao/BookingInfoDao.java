package com.upgrad.BookingService.dao;

import com.upgrad.BookingService.entities.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingInfoDao extends JpaRepository<BookingInfoEntity, Integer> {

}
