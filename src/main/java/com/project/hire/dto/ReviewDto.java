package com.project.hire.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto {
    private String message;
    private Long reviewer;
}
