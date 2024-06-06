package com.halil.bookwebsite.services;

import com.halil.bookwebsite.dtos.SignInResultDto;
import com.halil.bookwebsite.entities.User;

public interface UserService extends UserFunctionsService {
    User getByID(Long id);

    User update(User user);

    void deleteByID(Long id);

    User registerUser(User user);

    SignInResultDto loginUser(String username, String password);
}
