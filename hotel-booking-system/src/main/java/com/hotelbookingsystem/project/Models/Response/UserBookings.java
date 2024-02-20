package com.hotelbookingsystem.project.Models.Response;

import com.hotelbookingsystem.project.Models.Bookings;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserBookings {
    List<Bookings> bookingsList;
}
