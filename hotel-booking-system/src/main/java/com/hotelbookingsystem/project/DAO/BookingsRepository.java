package com.hotelbookingsystem.project.DAO;

import com.hotelbookingsystem.project.Models.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {
    List<Bookings> findAllByUserIdAndActive(Long userId, Boolean active);
}
