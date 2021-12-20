package com.upgrad.BookingService.services;

import com.upgrad.BookingService.Exceptions.InvalidBookingIdException;
import com.upgrad.BookingService.Exceptions.InvalidPaymentModeException;
import com.upgrad.BookingService.dao.BookingInfoDao;
import com.upgrad.BookingService.dto.BookingInfoDto;
import com.upgrad.BookingService.dto.BookingRequestDto;
import com.upgrad.BookingService.dto.PaymentRequestDTO;
import com.upgrad.BookingService.entities.BookingInfoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

@Component
public class BookingServiceImpl implements BookingService {

    ModelMapper modelMapper;
    BookingInfoDao bookingInfoDao;
    RestTemplate restTemplate;

    @Value("${api-gateway-url}")
    String apiGatewayUrl;


    @Autowired
    public BookingServiceImpl(ModelMapper modelMapper, BookingInfoDao bookingInfoDao, RestTemplate restTemplate) {
        this.modelMapper = modelMapper;
        this.bookingInfoDao = bookingInfoDao;
        this.restTemplate = restTemplate;
    }

    //returns a random number list
    public static ArrayList<String> getRandomNumbers(int count) {
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String> numberList = new ArrayList<String>();

        for (int i=0; i<count;i++){
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }
        return numberList;
    }

    @Override
    public BookingInfoDto getBookingInfo(BookingRequestDto bookingRequestDto) {

        // numOfDays, roomPrice and roomNumbers
        int numOfDays = (int) ChronoUnit.DAYS.between(bookingRequestDto.getFromDate(), bookingRequestDto.getToDate());
        int roomPrice = 1000 * bookingRequestDto.getNumOfRooms() * (numOfDays);
        String roomNumbers = String.join(",", getRandomNumbers(bookingRequestDto.getNumOfRooms()));

        BookingInfoEntity bookingInfoEntity = BookingInfoEntity.builder()
                .fromDate(bookingRequestDto.getFromDate())
                .toDate(bookingRequestDto.getToDate())
                .aadharNumber(bookingRequestDto.getAadharNumber())
                .transactionId(0)
                .numOfRooms(bookingRequestDto.getNumOfRooms())
                .roomNumbers(roomNumbers)
                .roomPrice(roomPrice)
                .bookedOn(LocalDate.now())
                .build();

        System.out.println(bookingInfoEntity + " " + numOfDays + " " + roomPrice + " " + roomNumbers);

        //Saving in Database
        bookingInfoDao.save(bookingInfoEntity);

        BookingInfoDto bookingInfoDto = modelMapper.map(bookingInfoEntity, BookingInfoDto.class);

        //return
        return bookingInfoDto;
    }

    @Override
    public BookingInfoDto addPaymentDetails(int bookingId, PaymentRequestDTO paymentRequestDTO) throws InvalidPaymentModeException, InvalidBookingIdException {

        // Calling payment-service API
        String paymentServiceUrl = apiGatewayUrl + "/payment/transaction";

        // Checking payment mode
        if(paymentRequestDTO.getPaymentMode().equals("CARD") || paymentRequestDTO.getPaymentMode().equals("UPI")) {
            // Checking booking Id
            if(!bookingInfoDao.existsById(bookingId)) {
                throw new InvalidBookingIdException();
            }

        int transactionId = restTemplate.postForObject(paymentServiceUrl, paymentRequestDTO, Integer.class);

            // Updating transactionId
            BookingInfoEntity bookingInfoEntity = bookingInfoDao.getById(bookingId);
            bookingInfoEntity.setTransactionId(transactionId);

            //Saving in database
            bookingInfoDao.save(bookingInfoEntity);

            //confirmation message
            String message = "Booking confirmed for user with aadhaar number: "
                    + bookingInfoEntity.getAadharNumber()
                    +    "    |    "
                    + "Here are the booking details:    " + bookingInfoEntity.toString();
            System.out.println(message);

            BookingInfoDto bookingInfoDTO = modelMapper.map(bookingInfoEntity, BookingInfoDto.class);
            return bookingInfoDTO;
            } else {
            throw new InvalidPaymentModeException();
        }
    }
}