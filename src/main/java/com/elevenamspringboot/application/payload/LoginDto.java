package com.elevenamspringboot.application.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotEmpty(message = "Email or Username is required to login!")
    private String usernameOrEmail;
    @NotEmpty(message = "Password is required to login!")
    private String password;
}
