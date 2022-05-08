package com.project.hire.services;

import com.project.hire.dto.BookingDto;
import com.project.hire.exceptions.NotFoundException;
import com.project.hire.models.Booking;
import com.project.hire.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final AnnouncementService announcementService;
    private final Converter<Booking, BookingDto> bookingConverter;


    @Transactional
    public List<BookingDto> getBookingsByAnnouncementId(long id, LocalDateTime start, LocalDateTime end) {
        return bookingRepository.getBookingsByAnnouncementId(id, start, end).stream()
                .map(bookingConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<BookingDto> getBookingsByAnnouncementId(long id, LocalDateTime start) {
        return bookingRepository.getBookingsByAnnouncementId(id, start).stream()
                .map(bookingConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<BookingDto> getBookingsByRenterId(long id, LocalDateTime start, LocalDateTime end) {
        return bookingRepository.getBookingsByRenterId(id, start, end).stream()
                .map(bookingConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<BookingDto> getBookingsByRenterId(long id, LocalDateTime start) {
        return bookingRepository.getBookingsByRenterId(id, start).stream()
                .map(bookingConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public void acceptBooking(long id) {
        Booking booking = findBooking(id);
        booking.setStatus(Booking.Status.ACCEPTED);

        bookingRepository.save(booking);
    }

    @Transactional
    public void rejectBooking(long id) {
        Booking booking = findBooking(id);
        booking.setStatus(Booking.Status.REJECTED);

        bookingRepository.save(booking);
    }

    @Transactional
    public void cancelBooking(long id) {
        Booking booking = findBooking(id);
        booking.setStatus(Booking.Status.CANCELED);

        bookingRepository.save(booking);
    }

    @Transactional
    public BookingDto getBooking(long id) {
        return bookingRepository.getBookingById(id)
                .map(bookingConverter::convert)
                .orElseThrow(NotFoundException::new);
    }



    @Transactional
    public void addBooking(BookingDto bookingDto) {
        Booking booking = new Booking();

        booking.setRenter(userService.findUserById(bookingDto.getRenter()));
        booking.setAnnouncement(announcementService.findAnnouncement(bookingDto.getAnnouncement()));
        booking.setStartTime(bookingDto.getStart());
        booking.setEndTime(booking.getEndTime());

        bookingRepository.save(booking);
    }

    Booking findBooking(long id) {
        return bookingRepository.getBookingById(id)
                .orElseThrow(NotFoundException::new);
    }
}
