package com.halil.bookwebsite.services.impl;

import com.halil.bookwebsite.entities.User;
import com.halil.bookwebsite.exceptions.NotFoundException;
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
            throw new NotFoundException("User not found");
        return userOptional.get();
    }

    @Override
    public User update(User user) {
        if(!userRepository.existsById(user.getId()))
            throw new NotFoundException("User not found");
        return userRepository.save(user);
    }

    @Override
    public void deleteByID(Long id) {
        if(!userRepository.existsById(id))
            throw new NotFoundException("User not found");
        userRepository.deleteById(id);
    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.get();
    }
}
