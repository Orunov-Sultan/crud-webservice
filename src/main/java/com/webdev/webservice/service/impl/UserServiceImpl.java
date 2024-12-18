package com.webdev.webservice.service.impl;

import com.webdev.webservice.dto.UserDTO;
import com.webdev.webservice.dto.UserResponse;
import com.webdev.webservice.entity.User;
import com.webdev.webservice.exception.EmailAlreadyExistsException;
import com.webdev.webservice.exception.UserNotFoundException;
import com.webdev.webservice.repository.UserRepository;
import com.webdev.webservice.service.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.query.SortDirection;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User checkUser = userRepository.findByEmail(userDTO.getEmail());
        if (checkUser != null) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        User user = modelMapper.map(userDTO, User.class);
        User createdUser = userRepository.save(user);
        return modelMapper.map(createdUser, UserDTO.class);
    }

    @Override
    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id " + id + " not found")
        );
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserResponse findAllUsers(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<User> users = userRepository.findAll(pageable);
        List<User> userList = users.getContent();

        List<UserDTO> content = userList.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();

        UserResponse userResponse = new UserResponse();
        userResponse.setContent(content);
        userResponse.setPageNo(users.getNumber());
        userResponse.setPageSize(users.getSize());
        userResponse.setTotalElements(users.getTotalElements());
        userResponse.setTotalPages(users.getTotalPages());
        userResponse.setLast(users.isLast());

        return userResponse;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id " + id + " not found")
        );
        User checkUser = userRepository.findByEmail(userDTO.getEmail());
        if (checkUser != null) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        User updatedUser = userRepository.save(user);

        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id " + id + " not found")
        );
        userRepository.delete(user);
    }
}
