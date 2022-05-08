package com.project.hire.dto.annoncement;

import com.project.hire.dto.ReviewDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AnnouncementDto {
    private Long id;

    private String title;
    private String description;
    private String address;
    private double hourPrice;

    private Long publisher;
    private List<ReviewDto> reviews;
    private List<Long> photos;
}
