package com.project.hire.converters;

import com.project.hire.dto.ReviewDto;
import com.project.hire.models.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {
    public ReviewDto convert(Review review) {
        return ReviewDto.builder()
                .message(review.getMessage())
                .reviewer(review.getReviewer().getId())
                .build();
    }
}
