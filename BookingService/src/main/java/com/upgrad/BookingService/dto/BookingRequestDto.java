package com.upgrad.BookingService.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequestDto {

    private LocalDate fromDate;
    private LocalDate toDate;
    private int numOfRooms;
    private String aadharNumber;

}