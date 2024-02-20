package com.hotelbookingsystem.project.Controller;

import com.hotelbookingsystem.project.Models.Request.BookingRequest;
import com.hotelbookingsystem.project.Models.Request.CancelBooking;
import com.hotelbookingsystem.project.Models.Response.AvailableRooms;
import com.hotelbookingsystem.project.Models.Response.UserBookings;
import com.hotelbookingsystem.project.Service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/rooms")
    private ResponseEntity<AvailableRooms> getAvailableRooms(@RequestHeader(required = false) String userId) {
        log.info("Get Available Rooms, userId: {}", userId);
        AvailableRooms response = bookingService.getAvailableRooms(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/book")
    private ResponseEntity<String> bookRoom(@RequestHeader Long userId, @RequestBody BookingRequest bookingRequest) {
        log.info("Book a room, userId: {}", userId);
        return ResponseEntity.ok(bookingService.bookRoom(userId, bookingRequest));
    }

    @GetMapping("/activeBookings")
    private ResponseEntity<UserBookings> getAvailableRooms(@RequestHeader Long userId) {
        log.info("Get active bookings, userId: {}", userId);
        return ResponseEntity.ok(bookingService.getActiveBookings(userId));
    }

    @GetMapping("/bookingHistory")
    private ResponseEntity<UserBookings> getBookingHistory(@RequestHeader Long userId) {
        log.info("Get Booking History, userId: {}", userId);
        return ResponseEntity.ok(bookingService.getBookingHistory(userId));
    }

    @DeleteMapping("/cancelBooking")
    private ResponseEntity<String> cancelBooking(@RequestHeader Long userId, @RequestBody CancelBooking cancelBooking) {
        log.info("Cancel Bookings, userId: {}", userId);
        return ResponseEntity.ok(bookingService.CancelBooking(userId, cancelBooking.getBookingId()));
    }
}
