package com.halil.bookwebsite.services.impl;

import com.halil.bookwebsite.entities.User;
import com.halil.bookwebsite.repositories.UserRepository;
import com.halil.bookwebsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public User getByID(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty())
            throw new RuntimeException();
        return userOptional.get();
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteByID(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty())
            throw new RuntimeException();
        if (userOptional.get().getPassword().equals(password))
            throw new RuntimeException();
        return userOptional.get();
    }
}
