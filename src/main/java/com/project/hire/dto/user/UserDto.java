package com.project.hire.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.hire.dto.ReviewDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;

    private List<ReviewDto> reviews;

    private Long photo;

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }
}
