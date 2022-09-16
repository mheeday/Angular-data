package com.example.demo.handler;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserHandler {
    private final UserService userService;

    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        try {
            userService.createUser(userDTO);
            return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
        } catch (Exception IllegalArgumentException){
            return new ResponseEntity<>("USERNAME EXISTS", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getUser(@PathVariable String username) {
        try {
            User user = userService.getUser(username);
            return new ResponseEntity<Object>(user, HttpStatus.ACCEPTED);
        } catch (Exception IllegalArgumentException){
            return new ResponseEntity<Object>("Username is invalid", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getAllUsers() {
        List<User> user = userService.getAllUsers();
        return new ResponseEntity<Object>(user, HttpStatus.ACCEPTED);
    }


    public ResponseEntity<Object> updateUser(String username, UserDTO userDTO) {
        try {
            userService.updateUser(username, userDTO);
            return new ResponseEntity<Object>("UPDATED", HttpStatus.ACCEPTED);
        } catch (Exception e){
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
            if (e.getClass().equals(illegalArgumentException.getClass())) {
                if (e.getMessage().equals("Exist")) {
                    return new ResponseEntity<Object>("Username Exists", HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<Object>("No user found", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<Object>("BAD REQUEST", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> deleteUser(String username) {
        try {
            userService.deleteUser(username);
            return new ResponseEntity<Object>("DELETED", HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<Object>("No user found", HttpStatus.BAD_REQUEST);
        }
    }
}
