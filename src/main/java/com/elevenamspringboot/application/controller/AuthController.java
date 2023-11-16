package com.elevenamspringboot.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elevenamspringboot.application.payload.AuthDto;
import com.elevenamspringboot.application.payload.LoginDto;
import com.elevenamspringboot.application.payload.SignUpDto;
import com.elevenamspringboot.application.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpDto> signUp(@RequestBody SignUpDto signUpDto) {
        return new ResponseEntity<>(authService.signUp(signUpDto), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthDto> signUp(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(authService.login(loginDto), HttpStatus.ACCEPTED);
    }

}
