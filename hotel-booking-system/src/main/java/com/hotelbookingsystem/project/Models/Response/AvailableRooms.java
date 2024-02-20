package com.hotelbookingsystem.project.Models.Response;

import com.hotelbookingsystem.project.Models.Rooms;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AvailableRooms {
    private List<Rooms> roomsList;
}
