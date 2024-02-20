package com.hotelbookingsystem.project.DAO;

import com.hotelbookingsystem.project.Models.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomsRepository extends JpaRepository<Rooms, Long> {
    List<Rooms> findAllByAvailable(Boolean available);

    Optional<Rooms> findByIdAndAvailable(Long parseLong, Boolean available);
}
