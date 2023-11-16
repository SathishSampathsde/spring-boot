package com.elevenamspringboot.application.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.elevenamspringboot.application.entity.User;
import com.elevenamspringboot.application.exception.ApiException;
import com.elevenamspringboot.application.payload.AuthDto;
import com.elevenamspringboot.application.payload.LoginDto;
import com.elevenamspringboot.application.payload.SignUpDto;
import com.elevenamspringboot.application.repository.UserRepository;
import com.elevenamspringboot.application.security.JwtTokenProvider;
import com.elevenamspringboot.application.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private ModelMapper mapper;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(UserRepository userRepository, ModelMapper mapper, PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public SignUpDto signUp(SignUpDto signUpDto) {
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email is already exists!");
        }
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Username is already exists!");
        }

        User user = new User();

        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        User newUser = userRepository.save(user);

        SignUpDto signUpResponse = mapper.map(newUser, SignUpDto.class);
        return signUpResponse;
    }

    @Override
    public AuthDto login(LoginDto loginDto) {
        User user =
        userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(),
        
        loginDto.getUsernameOrEmail());

        if (user == null) {
        throw new UsernameNotFoundException("Email or Username is incorrect!");
        }

        if (!BCrypt.checkpw(loginDto.getPassword(), user.getPassword())) {
        throw new ApiException(HttpStatus.UNAUTHORIZED, "Password is incorrect!");
        }

        String token = jwtTokenProvider.generateToken(user.getEmail());

        AuthDto authResponse = new AuthDto(true, token);
        return authResponse;
    }

}
