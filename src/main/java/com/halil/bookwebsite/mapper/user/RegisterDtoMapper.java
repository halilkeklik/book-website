package com.halil.bookwebsite.mapper.user;

import com.halil.bookwebsite.dtos.UserRegisterDto;
import com.halil.bookwebsite.entities.User;
import com.halil.bookwebsite.mapper.Mapper;
import jdk.jfr.Category;
import org.springframework.stereotype.Component;

@Component
public class RegisterDtoMapper extends Mapper<User, UserRegisterDto> {
    @Override
    public UserRegisterDto maptoB(User user) {
        return modelMapper.map(user, UserRegisterDto.class);
    }

    @Override
    public User mapfromA(UserRegisterDto userRegisterDto) {
        return modelMapper.map(userRegisterDto, User.class);
    }
}
