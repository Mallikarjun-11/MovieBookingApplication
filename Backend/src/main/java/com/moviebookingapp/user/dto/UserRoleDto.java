package com.moviebookingapp.user.dto;

import com.moviebookingapp.user.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {
    private String userName;
    private Set<String> roles;
}