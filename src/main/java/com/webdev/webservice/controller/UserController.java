package com.webdev.webservice.controller;

import com.webdev.webservice.dto.UserDTO;
import com.webdev.webservice.dto.UserResponse;
import com.webdev.webservice.entity.User;
import com.webdev.webservice.service.UserService;
import com.webdev.webservice.util.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
@Tag(
        name = "CRUD Rest API для пользователя",
        description = "Создать польователя, Обновить Пользователя, Найти Пользователя, Найти всех Пользователей, Удалить Пользователя"
)
public class UserController {
    private UserService userService;

    @Operation(
            summary = "Создание пользователя Rest API",
            description = "Сохранение данных о пользователе в базе данных"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED пользователь создан"
    )
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Получение пользователя Rest API",
            description = "Получение пользователя по Id из базы данных"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK операция прошла успешно"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.findUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    @Operation(
            summary = "Получение всез пользователей Rest API",
            description = "Получение всех пользователей из базы данных"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK операция прошла успешно"
    )
    @GetMapping
    public ResponseEntity<UserResponse> findAllUsers(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        UserResponse users = userService.findAllUsers(pageNo, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(users);
    }

    @Operation(
            summary = "Обновление пользователя Rest API",
            description = "Обновление пользователя из базы данных"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK операция прошла успешно"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(
            summary = "Удаление пользователя Rest API",
            description = "Удаление пользователя по Id из базы данных"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK операция прошла успешно"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}
