package com.halil.bookwebsite.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserLoginDto {
    @NonNull
    private String username;

    @NonNull
    private String password;
}
