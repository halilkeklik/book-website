package com.halil.blogwebsite.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Mapper<A,B> {

    @Autowired
    protected ModelMapper modelMapper;

    public abstract B maptoB(A a);

    public abstract A mapfromA(B b);
}
