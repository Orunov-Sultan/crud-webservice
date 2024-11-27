package com.webdev.webservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    @NotEmpty(message = "Это поле обязательно для заполнения")
    private String firstName;

    @NotEmpty(message = "Это поле обязательно для заполнения")
    private String lastName;

    @NotEmpty(message = "Это поле обязательно для заполнения")
    @Email(message = "Не корректный адрес электронной почты")
    private String email;
}
