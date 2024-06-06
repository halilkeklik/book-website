package com.halil.bookwebsite.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignInResultDto {

    private Long id;

    private String username;

    private String token;

    private String type;
}
