package com.example.demo.serviceimpl;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public void createUser(UserDTO userDTO) {
        User newUser = new User();
        newUser.setAge(userDTO.getAge());
        newUser.setUsername(userDTO.getUsername());
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());

        try {
        userRepo.save(newUser);}
        catch (Exception SQLIntegrityConstraintViolationException) {
            throw new IllegalArgumentException("Username " + userDTO.getUsername() + " taken");
        }
    }

    @Override
    public User getUser(String username) {
        Optional<User> user = userRepo.findUserByUsername(username);
        System.out.println(username);
        System.out.println(user);
        if (user.isPresent()) {
            return user.get();
        }
        throw new IllegalArgumentException("Doesn't exist");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public void updateUser(String username, UserDTO userDTO) {
        User oldUser = this.getUser(username);
        System.out.println(username);
        System.out.println(oldUser);

        oldUser.setAge(userDTO.getAge());
        oldUser.setUsername(userDTO.getUsername());
        oldUser.setFirstName(userDTO.getFirstName());
        oldUser.setLastName(userDTO.getLastName());

        try {
            userRepo.save(oldUser);
        } catch (Exception SQLIntegrityConstraintViolationException) {
            throw new IllegalArgumentException("Exist");

        }
    }
    @Override
    public void deleteUser(String username) {
        Optional<User> oldUser = userRepo.findUserByUsername(username);
        if (oldUser.isPresent()){
            userRepo.delete(oldUser.get());
            return;
        }
        throw new IllegalArgumentException("User with username " + username + " doesn't exist");
    }
}
