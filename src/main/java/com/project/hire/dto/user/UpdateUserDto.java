package com.project.hire.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UpdateUserDto {
    @JsonIgnore
    private Long id;

    @Size(min = 5, max = 100)
    private String name;

    @Email
    private String email;

    private String phoneNumber;

    private String photo;
}
