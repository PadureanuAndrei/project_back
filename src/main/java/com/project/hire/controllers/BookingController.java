package com.project.hire.controllers;

import com.project.hire.dto.BookingDto;
import com.project.hire.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public void addBooking(@RequestBody BookingDto booking) {
        bookingService.addBooking(booking);
    }

    @GetMapping
    public List<BookingDto> getAnnouncementBookings(
            @RequestParam long announcementId,
            @RequestParam LocalDateTime start,
            @RequestParam Optional<LocalDateTime> end) {

        if (end.isPresent()) {
            return bookingService.getBookingsByAnnouncementId(announcementId, start, end.get());
        }

        return bookingService.getBookingsByAnnouncementId(announcementId, start);
    }

    @GetMapping("/user")
    public List<BookingDto> getUserBookings(
            @RequestParam long userId,
            @RequestParam LocalDateTime start,
            @RequestParam Optional<LocalDateTime> end) {

        if (end.isPresent()) {
            return bookingService.getBookingsByRenterId(userId, start, end.get());
        }

        return bookingService.getBookingsByRenterId(userId, start);
    }
}
