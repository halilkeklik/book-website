package com.halil.blogwebsite.mapper.user;

import com.halil.blogwebsite.dtos.UserRegisterDto;
import com.halil.blogwebsite.entities.User;
import com.halil.blogwebsite.mapper.Mapper;

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
