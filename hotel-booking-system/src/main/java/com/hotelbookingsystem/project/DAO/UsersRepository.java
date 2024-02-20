package com.hotelbookingsystem.project.DAO;

import com.hotelbookingsystem.project.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
