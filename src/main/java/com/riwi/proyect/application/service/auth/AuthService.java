package com.riwi.proyect.application.service.auth;

import com.riwi.proyect.application.dtos.requests.AuthUserRequestDto;
import com.riwi.proyect.application.dtos.responses.AuthUserResponseDto;
import com.riwi.proyect.domain.entities.Users;
import com.riwi.proyect.domain.exception.InvalidCredentialException;
import com.riwi.proyect.domain.ports.service.interfaces.IAuthService;
import com.riwi.proyect.infrastructure.helpers.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthUserResponseDto login(AuthUserRequestDto requestDto) {
        Users user = (Users) userDetailsService.loadUserByUsername(requestDto.getIdentifier());

        if(!passwordEncoder.matches(requestDto.getIdentifier(), requestDto.getPassword())){
            throw new InvalidCredentialException("Invalid credential");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getIdentifier(), requestDto.getPassword()));

        return AuthUserResponseDto.builder()
                .message(user.getRole() + " Successfully authentication")
                .token(jwtUtil.generateToken(user))
                .build();
    }
}
