package com.rise.controllers;

import com.rise.entity.Meal;
import com.rise.entity.MealBooking;
import com.rise.service.MealBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private MealBookingService bookingService;

    @PostMapping
    public ResponseEntity<List<MealBooking>> bookMultipleDays(@RequestBody Map<String, Object> requestData) {
        Long userId = Long.valueOf(requestData.get("userId").toString());
        LocalDate startDate = LocalDate.parse(requestData.get("startDate").toString());
        LocalDate endDate = LocalDate.parse(requestData.get("endDate").toString());

        List<MealBooking> bookedBookings = bookingService.bookMultipleDays(userId, startDate, endDate);
        return ResponseEntity.ok(bookedBookings);
    }


    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok("Booking canceled successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<MealBooking>> getBookingsByUser(@PathVariable Long userId) {
        List<MealBooking> userBookings = bookingService.getBookingsByUser(userId);
        return ResponseEntity.ok(userBookings);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MealBooking>> getAllBookings() {
        List<MealBooking> allBookings = bookingService.getAllBookings();
        return ResponseEntity.ok(allBookings);
    }
}
