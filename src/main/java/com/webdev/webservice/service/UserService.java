package com.webdev.webservice.service;

import com.webdev.webservice.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO findUserById(Long id);
    List<UserDTO> findAllUsers();
    UserDTO updateUser(Long id, UserDTO userDTO);
}
