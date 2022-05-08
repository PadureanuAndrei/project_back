package com.project.hire.converters;

import com.project.hire.dto.ReviewDto;
import com.project.hire.dto.annoncement.AnnouncementDto;
import com.project.hire.models.Announcement;
import com.project.hire.models.File;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AnnouncementConverter implements Converter<Announcement, AnnouncementDto> {
    private final ReviewConverter reviewConverter;

    @Override
    public AnnouncementDto convert(Announcement announcement) {
        List<Long> photosId = announcement.getPhotos().stream()
                .map(File::getId)
                .collect(Collectors.toList());

        List<ReviewDto> reviews = announcement.getReviews().stream()
                .map(reviewConverter::convert)
                .collect(Collectors.toList());

        return AnnouncementDto.builder()
                .id(announcement.getId())
                .title(announcement.getTitle())
                .description(announcement.getDescription())
                .address(announcement.getAddress())
                .hourPrice(announcement.getHourPrice())
                .reviews(reviews)
                .photos(photosId)
                .publisher(announcement.getPublisher().getId())
                .build();
    }
}
