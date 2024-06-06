package com.halil.bookwebsite.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserLoginDto {
    @NotNull
    private String username;

    @NotNull
    private String password;
}
