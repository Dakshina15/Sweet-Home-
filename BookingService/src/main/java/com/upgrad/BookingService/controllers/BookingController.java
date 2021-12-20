package com.upgrad.BookingService.controllers;

import com.upgrad.BookingService.Exceptions.InvalidBookingIdException;
import com.upgrad.BookingService.Exceptions.InvalidPaymentModeException;
import com.upgrad.BookingService.dto.BookingInfoDto;
import com.upgrad.BookingService.dto.BookingRequestDto;
import com.upgrad.BookingService.dto.PaymentRequestDTO;
import com.upgrad.BookingService.services.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    /**
     * USING POST METHOD - "/booking"
     * @param bookingRequestDto
     * return
     */

    @PostMapping("/booking")
    public ResponseEntity<BookingInfoDto> getBookingInfo(@RequestBody BookingRequestDto bookingRequestDto){
        BookingInfoDto bookingInfoDto = bookingService.getBookingInfo(bookingRequestDto);
        return new ResponseEntity<>(bookingInfoDto, HttpStatus.CREATED);
    }


    /**
     * Using POST METHOD- "/booking/{bookingId}/transaction"
     * Passing @param bookingId, paymentRequestDto
     * @return
     *
     */
    @PostMapping("/booking/{bookingId}/transaction")
    public ResponseEntity<BookingInfoDto> addPaymentDetails(@PathVariable(name = "bookingId") int bookingId, @RequestBody PaymentRequestDTO paymentRequestDto) throws InvalidBookingIdException, InvalidPaymentModeException {
        BookingInfoDto bookingInfoDto = bookingService.addPaymentDetails(bookingId, paymentRequestDto);
        return new ResponseEntity<>(bookingInfoDto, HttpStatus.CREATED);
    }

}