package com.hotelbookingsystem.project.Models.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    private Long roomId;
    private String checkInDate;//dd-mm-yyyy
    private String checkOutDate;//dd-mm-yyyy
}
