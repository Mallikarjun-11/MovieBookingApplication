package com.moviebookingapp.user.entities;

import lombok.Data;

@Data
public class JwtRequest {
    private String userName;
    private String password;
}
