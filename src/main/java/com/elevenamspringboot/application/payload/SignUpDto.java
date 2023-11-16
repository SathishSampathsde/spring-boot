package com.elevenamspringboot.application.payload;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    private long id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
}
