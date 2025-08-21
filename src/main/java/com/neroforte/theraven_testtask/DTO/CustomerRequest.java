package com.neroforte.theraven_testtask.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRequest {


    @Size(min = 2, max = 50, message = "Full name must be between 2 and 50 characters, including whitespaces.")
    private String fullName;

    @Size(min = 2, max = 100, message = "Email must be between 2 and 100 characters.")
    @Email(message = "Email must be a valid email address.")
    private String email;

    @Pattern(regexp = "^\\+?[0-9-]{6,14}$", message = "Phone must be 6-14 characters, containing digits and start with an optional plus sign.")
    private String phone;


}
