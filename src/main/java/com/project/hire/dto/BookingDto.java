package com.project.hire.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingDto {
    private Long id;

    private LocalDateTime start;
    private LocalDateTime end;

    private Long renter;
    private Long announcement;
}
