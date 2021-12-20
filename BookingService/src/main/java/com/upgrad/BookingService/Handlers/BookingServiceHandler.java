package com.upgrad.BookingService.Handlers;

import com.upgrad.BookingService.Exceptions.InvalidBookingIdException;
import com.upgrad.BookingService.Exceptions.InvalidPaymentModeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class BookingServiceHandler {

    /**
     * Handling exception
     * @param e
     * @return
     */
    @ExceptionHandler
    public ResponseEntity handlePaymentModeException(InvalidPaymentModeException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", "Invalid mode of payment");
        errorResponse.put("statusCode", 400);
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }


    /**
     * Handling exception
     * @param e
     * @return
     */
    @ExceptionHandler
    public ResponseEntity handleInvalidBookingIdException(InvalidBookingIdException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", "Invalid Booking Id");
        errorResponse.put("statusCode", 400);
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }
}


