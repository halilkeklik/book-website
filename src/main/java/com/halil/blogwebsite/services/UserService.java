package com.halil.blogwebsite.services;

import com.halil.blogwebsite.entities.User;

public interface UserService {
    User getByID(Long id);

    User update(User user);

    void deleteByID(Long id);

    User registerUser(User user);

    User loginUser(String username, String password);
}
