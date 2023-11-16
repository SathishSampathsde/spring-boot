package com.elevenamspringboot.application.service;

import com.elevenamspringboot.application.payload.AuthDto;
import com.elevenamspringboot.application.payload.LoginDto;
import com.elevenamspringboot.application.payload.SignUpDto;

public interface AuthService {
    SignUpDto signUp(SignUpDto signUpDto);
    AuthDto login(LoginDto loginDto);
}