package com.riwi.proyect.application.service.auth;

import com.riwi.proyect.application.dtos.requests.UserRequestDto;
import com.riwi.proyect.application.dtos.responses.AuthUserResponseDto;
import com.riwi.proyect.application.dtos.responses.UserResponseDto;
import com.riwi.proyect.application.mappers.UserMapper;
import com.riwi.proyect.domain.entities.Users;
import com.riwi.proyect.domain.enums.RoleEnum;
import com.riwi.proyect.domain.exception.InvalidCredentialException;
import com.riwi.proyect.domain.ports.service.interfaces.IUserService;
import com.riwi.proyect.infrastructure.helpers.JwtUtil;
import com.riwi.proyect.infrastructure.persistence.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    @Autowired
    public final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JwtUtil jwtUtil;

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found!");
        }

        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDto> readAll() {
        List<Users> listUser = userRepository.findAll();

        return listUser.stream()
                .map(UserMapper.INSTANCE::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto readById(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));
        return UserMapper.INSTANCE.toResponseDto(user);
    }

    @Override
    public Users update(UserRequestDto requestDto, Long id) {
        return userRepository.findById(id).map(users -> {
            Users user = UserMapper.INSTANCE.toEntity(requestDto);
            user.setId(users.getId());
            return userRepository.save(user);
        })
                .orElseThrow(() -> new EntityNotFoundException("User with ID: " + "'" + id + "'" + " not found!"));
    }

    @Override
    public AuthUserResponseDto register(UserRequestDto request, RoleEnum role) {
        Users userDB = userRepository.findByUsernameOrEmail(request.getUsername(), request.getEmail());

        if (userDB != null) {
            throw new InvalidCredentialException("Registered user!");
        }

        Users user = Users.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .role(role)
                .enabled(true)
                .build();

        user = this.userRepository.save(user);

        return AuthUserResponseDto.builder()
                .message(user.getRole() + " successfully authenticated!")
                .token(this.jwtUtil.generateToken(user))
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
