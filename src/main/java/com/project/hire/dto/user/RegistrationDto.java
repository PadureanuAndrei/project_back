package com.project.hire.dto.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationDto {
    @NotNull
    @Size(min = 5, max = 100)
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String password;
}
