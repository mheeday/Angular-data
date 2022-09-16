package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    void createUser(UserDTO userDTO);
    User getUser (String username);
    List<User> getAllUsers ();
    void updateUser (String username, UserDTO userDTO);
    void deleteUser (String username);
}
