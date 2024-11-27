package com.webdev.webservice.service.impl;

import com.webdev.webservice.dto.UserDTO;
import com.webdev.webservice.entity.User;
import com.webdev.webservice.repository.UserRepository;
import com.webdev.webservice.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User createdUser = userRepository.save(user);
        return modelMapper.map(createdUser, UserDTO.class);
    }
}
