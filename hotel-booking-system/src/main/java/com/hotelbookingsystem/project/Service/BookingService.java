package com.hotelbookingsystem.project.Service;

import com.hotelbookingsystem.project.Models.Request.BookingRequest;
import com.hotelbookingsystem.project.Models.Response.UserBookings;
import com.hotelbookingsystem.project.Models.Response.AvailableRooms;

public interface BookingService {
    AvailableRooms getAvailableRooms(String userId);
    String bookRoom(Long userId, BookingRequest bookingRequest);
    UserBookings getActiveBookings(Long userId);
    UserBookings getBookingHistory(Long userId);
    String CancelBooking(Long userId, Long bookingId);
}
