package com.halil.bookwebsite.controllers;

import com.halil.bookwebsite.dtos.SignInResultDto;
import com.halil.bookwebsite.dtos.UserLoginDto;
import com.halil.bookwebsite.dtos.UserRegisterDto;
import com.halil.bookwebsite.entities.User;
import com.halil.bookwebsite.mapper.user.RegisterDtoMapper;
import com.halil.bookwebsite.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RegisterDtoMapper registerDtoMapper;

    @PreAuthorize("@authComponent.hasPermission('manageUserAdmin') or @authComponent.hasPermission('manageUserSelf', userService, #id)")
    @GetMapping("/private/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getByID(id), HttpStatus.OK);
    }

    @PreAuthorize("@authComponent.hasPermission('manageUserAdmin') or @authComponent.hasPermission('manageUserSelf', userService, #id)")
    @PutMapping("/private/user/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @PreAuthorize("@authComponent.hasPermission('manageUserAdmin') or @authComponent.hasPermission('manageUserSelf', userService, #id)")
    @DeleteMapping("/private/user/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id) {
        userService.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/public/auth/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        User user = registerDtoMapper.mapfromA(userRegisterDto);
        User savedUserEntity = userService.registerUser(user);
        return new ResponseEntity<>(savedUserEntity, HttpStatus.CREATED);
    }

    @PostMapping("/public/auth/login")
    public ResponseEntity<SignInResultDto> loginUser(@Valid @RequestBody UserLoginDto userLoginDto) {
        SignInResultDto resultDto = userService.loginUser(userLoginDto.getUsername(),userLoginDto.getPassword());
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }
}
