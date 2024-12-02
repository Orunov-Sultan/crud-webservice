package com.webdev.webservice.service;

import com.webdev.webservice.dto.UserDTO;
import com.webdev.webservice.dto.UserResponse;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO findUserById(Long id);
    UserResponse findAllUsers(int pageNo, int pageSize, String sortBy, String sortDir);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}
