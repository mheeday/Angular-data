package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:57480", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/user/")
public class UserController {

    private final UserHandler userHandler;

    @PostMapping("create")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        return userHandler.createUser(userDTO);
    }

    @GetMapping("{username}")
    public ResponseEntity<Object> getUser(@PathVariable String username) {
        return userHandler.getUser(username);
    }

    @GetMapping("all")
    public ResponseEntity<Object> getAllUsers() {
        return userHandler.getAllUsers();
    }

    @PostMapping("update/{username}")
    public ResponseEntity<Object> updateUser(@PathVariable String username,
                                                @RequestBody UserDTO userDTO) {
        return userHandler.updateUser(username, userDTO);
    }

    @PostMapping("delete/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable String username) {
        return userHandler.deleteUser(username);
    }
}
