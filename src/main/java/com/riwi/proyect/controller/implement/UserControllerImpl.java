package com.riwi.proyect.controller.implement;

import com.riwi.proyect.application.dtos.requests.UserRequestDto;
import com.riwi.proyect.application.dtos.responses.AuthUserResponseDto;
import com.riwi.proyect.application.dtos.responses.UserResponseDto;
import com.riwi.proyect.controller.interfaces.IUserController;
import com.riwi.proyect.domain.entities.Users;
import com.riwi.proyect.domain.enums.RoleEnum;
import com.riwi.proyect.domain.ports.service.interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor
@Tag(name = "User", description = "Manage user-related request.")
@CrossOrigin("*")
public class UserControllerImpl implements IUserController {

    @Autowired
    private final IUserService userService;

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete a user.",
            description = "Delete a user by their ID, requiring authentication."
    )
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("readAll")
    @Operation(
            summary = "List all users.",
            description = "Provide the token to validate the permissions and obtain the list of users."
    )
    @Override
    public ResponseEntity<List<UserResponseDto>> readAll() {
        return ResponseEntity.ok(this.userService.readAll());
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("{id}")
    @Operation(
            summary = "Get user by ID.",
            description = "Retrieve a user's details by their ID, with proper authentication."
    )
    @Override
    public ResponseEntity<UserResponseDto> readById(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.readById(id));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("{id}")
    @Operation(
            summary = "Update user by ID.",
            description = "Update a user's details by their ID, requiring authentication."
    )
    @Override
    public ResponseEntity<Users> update(@Validated @PathVariable UserRequestDto requestDto,
                                        @PathVariable Long id) {
        return ResponseEntity.ok(this.userService.update(requestDto, id));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("register/admin")
    @Operation(
            summary = "Create an admin.",
            description = "Provides the user data to create it and token to validated the permissions."
    )
    @Override
    public ResponseEntity<AuthUserResponseDto> registerAdmin(@Validated @RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(this.userService.register(requestDto, RoleEnum.ADMIN));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("register/user ")
    @Operation(
            summary = "Create an regular user.",
            description = "Provides the user data to create it and token to validated the permissions."
    )
    @Override
    public ResponseEntity<AuthUserResponseDto> registerRegularUser(@Validated @RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(this.userService.register(requestDto, RoleEnum.REGULAR_USER));
    }
}
