package com.bootcamp.bootcamp17.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank(message = "first name wajib diisi")
    private String firstName;

    @NotBlank(message = "last name wajib diisi")
    private String lastName;

    @Email(message = "Format email tidak valid")
    @NotBlank(message = "Email wajib diisi")
    private String email;

    @NotBlank(message = "Phone number wajib diisi")
    private String phoneNumber;
}