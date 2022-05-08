package com.project.hire.dto.annoncement;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateAnnouncementDto {
    private String title;
    private String description;
    private String address;
    private Double hourPrice;

    private List<String> photos = new ArrayList<>();
}
