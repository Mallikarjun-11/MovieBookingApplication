package com.moviebookingapp.user.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtResponse {

   // private User user;
    private String jwtToken;
}
