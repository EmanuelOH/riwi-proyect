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
import org.springframework.transaction.annotation.Transactional;

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


    @Transactional
    @Override
    public AuthUserResponseDto login(AuthUserRequestDto request) {
        Users user = (Users) userDetailsService.loadUserByUsername(request.getIdentifier());

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw  new InvalidCredentialException("Invalid credential");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getIdentifier(), request.getPassword()));

        return AuthUserResponseDto.builder()
                .message(user.getRole() + "Succesfull authentication")
                .token(this.jwtUtil.generateToken(user))
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}
