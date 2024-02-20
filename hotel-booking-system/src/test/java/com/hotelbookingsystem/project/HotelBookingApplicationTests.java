package com.hotelbookingsystem.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelbookingsystem.project.Models.Request.BookingRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HotelBookingApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Container
	private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.28")
			.withDatabaseName("hotelbooking")
			.withUsername("root")
			.withPassword("root");

	@Test
	@Order(1)
	void getAvailableRooms() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/rooms")
						.header("userId", "1"))
				.andExpect(status().isOk());
	}

	@Test
	@Order(2)
	void bookRoom() throws Exception {
		BookingRequest bookingRequest = createBookingRequest();

		// Convert BookingRequest to JSON string
		String requestStr = objectMapper.writeValueAsString(bookingRequest);

		// Perform POST request to book a room
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/book")
						.header("userId", "1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestStr))
				.andExpect(status().isOk())
				.andReturn();

		String responseContent = mvcResult.getResponse().getContentAsString();

		Assertions.assertTrue(responseContent.equals("Room booked successfully.")
				|| responseContent.equals("Room not available, Please try again."));
	}

	private BookingRequest createBookingRequest() {
		return BookingRequest.builder().roomId(2L).checkInDate("25-02-2024").checkOutDate("27-02-2024").build();
	}
}
