package com.project.hire.converters;

import com.project.hire.dto.BookingDto;
import com.project.hire.models.Booking;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookingConverter implements Converter<Booking, BookingDto> {
    @Override
    public BookingDto convert(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .end(booking.getEndTime())
                .start(booking.getStartTime())
                .renter(booking.getRenter().getId())
                .announcement(booking.getAnnouncement().getId())
                .build();
    }
}
