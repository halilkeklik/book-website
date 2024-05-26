package com.halil.blogwebsite.controllers;

import com.halil.blogwebsite.dtos.UserLoginDto;
import com.halil.blogwebsite.dtos.UserRegisterDto;
import com.halil.blogwebsite.entities.User;
import com.halil.blogwebsite.mapper.user.RegisterDtoMapper;
import com.halil.blogwebsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RegisterDtoMapper registerDtoMapper;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getByID(id), HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return new ResponseEntity<>(userService.update(user),HttpStatus.OK);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id) {
        userService.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/user/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        User newUser = registerDtoMapper.mapfromA(userRegisterDto);
        return new ResponseEntity<>(userService.registerUser(newUser),HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public ResponseEntity<User> loginUser(@RequestBody UserLoginDto userLoginDto) {
        return new ResponseEntity<>(userService.loginUser(userLoginDto.getUsername(),userLoginDto.getPassword()),HttpStatus.OK);
    }
}
