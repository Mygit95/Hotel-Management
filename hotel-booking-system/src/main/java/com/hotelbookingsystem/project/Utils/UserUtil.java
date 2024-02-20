package com.hotelbookingsystem.project.Utils;

import com.hotelbookingsystem.project.Models.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserUtil {

    public Users CreateUser(Long userId) {
        return Users.builder().userId(userId).build();
    }
}
