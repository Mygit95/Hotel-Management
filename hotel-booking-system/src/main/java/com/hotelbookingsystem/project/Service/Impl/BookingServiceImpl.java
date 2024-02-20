package com.hotelbookingsystem.project.Service.Impl;

import com.hotelbookingsystem.project.DAO.BookingsRepository;
import com.hotelbookingsystem.project.DAO.RoomsRepository;
import com.hotelbookingsystem.project.DAO.UsersRepository;
import com.hotelbookingsystem.project.Models.Bookings;
import com.hotelbookingsystem.project.Models.Request.BookingRequest;
import com.hotelbookingsystem.project.Models.Response.AvailableRooms;
import com.hotelbookingsystem.project.Models.Response.UserBookings;
import com.hotelbookingsystem.project.Models.Rooms;
import com.hotelbookingsystem.project.Models.Users;
import com.hotelbookingsystem.project.Service.BookingService;
import com.hotelbookingsystem.project.Utils.DateUtil;
import com.hotelbookingsystem.project.Utils.UserUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BookingsRepository bookingsRepository;

    @Autowired
    private RoomsRepository roomsRepository;

    private final DateUtil dateUtil;

    private final UserUtil userUtil;

    public BookingServiceImpl(DateUtil dateUtil, UserUtil userUtil) {
        this.dateUtil = dateUtil;
        this.userUtil = userUtil;
    }

    @Override
    public AvailableRooms getAvailableRooms(String userId) {
        List<Rooms> roomsList = roomsRepository.findAllByAvailable(true);
        Collections.shuffle(roomsList);
        return AvailableRooms.builder().roomsList(roomsList).build();
    }

    @Override
    @Transactional
    public String bookRoom(Long userId, BookingRequest bookingRequest) {

        Optional<Users> user = usersRepository.findById(userId);

        //Create a user
        if(user.isEmpty()) {
            user = Optional.ofNullable(userUtil.CreateUser(userId));
            usersRepository.save(user.get());
        }

        Optional<Rooms> roomsOptional = roomsRepository.findByIdAndAvailable(bookingRequest.getRoomId(), true);

        if (roomsOptional.isPresent()) {
            Rooms room = roomsOptional.get();

            Bookings booking = Bookings.builder()
                    .userId(userId)
                    .roomId(room.getId())
                    .active(true)
                    .bookingCreatedAt(LocalDateTime.now())
                    .checkInDate(dateUtil.parseDate(bookingRequest.getCheckInDate()))
                    .checkOutDate(dateUtil.parseDate(bookingRequest.getCheckOutDate()))
                    .cancelled(false)
                    .build();

            bookingsRepository.save(booking);

            room.setAvailable(false);
            roomsRepository.save(room);

            return "Room booked successfully.";
        }

        return "Room not available, Please try again.";
    }

    @Override
    public UserBookings getActiveBookings(Long userId) {
        return UserBookings.builder()
                .bookingsList(bookingsRepository.findAllByUserIdAndActive(userId, true)).build();
    }

    @Override
    public UserBookings getBookingHistory(Long userId) {
        return UserBookings.builder()
                .bookingsList(bookingsRepository.findAllByUserIdAndActive(userId, false)).build();
    }

    @Override
    @Transactional
    public String CancelBooking(Long userId, Long bookingId) {

        Optional<Bookings> bookings = bookingsRepository.findById(bookingId);

        if(bookings.isEmpty() || bookings.get().getUserId() != userId) {
            return "No such a bookingId linked to your userId exists!";
        }

        //Cancel the booking
        bookings.get().setCancelled(true);
        bookings.get().setActive(false);
        bookingsRepository.save(bookings.get());

        //Make the room available for other users
        Optional<Rooms> rooms = roomsRepository.findById(bookings.get().getRoomId());
        rooms.get().setAvailable(true);
        roomsRepository.save(rooms.get());

        return "Booking canceled successfully";
    }
}
