package com.webdev.webservice.service.impl;

import com.webdev.webservice.dto.UserDTO;
import com.webdev.webservice.entity.User;
import com.webdev.webservice.repository.UserRepository;
import com.webdev.webservice.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id).get();
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).get();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        User updatedUser = userRepository.save(user);

        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }
}
