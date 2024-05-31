package com.halil.bookwebsite.services;

import com.halil.bookwebsite.entities.User;

public interface UserService {
    User getByID(Long id);

    User update(User user);

    void deleteByID(Long id);

    User registerUser(User user);

    User loginUser(String username, String password);
}
