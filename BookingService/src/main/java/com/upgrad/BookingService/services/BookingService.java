package com.upgrad.BookingService.services;

import com.upgrad.BookingService.Exceptions.InvalidBookingIdException;
import com.upgrad.BookingService.Exceptions.InvalidPaymentModeException;
import com.upgrad.BookingService.dto.BookingInfoDto;
import com.upgrad.BookingService.dto.BookingRequestDto;
import com.upgrad.BookingService.dto.PaymentRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {
    BookingInfoDto getBookingInfo(BookingRequestDto bookingRequestDto);


    BookingInfoDto addPaymentDetails(int bookingId, PaymentRequestDTO paymentRequestDTO) throws InvalidPaymentModeException, InvalidBookingIdException;

}
