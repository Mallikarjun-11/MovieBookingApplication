package com.moviebookingapp.user.service;

import com.moviebookingapp.user.dto.UserRoleDto;
import com.moviebookingapp.user.entities.Movie;
import com.moviebookingapp.user.entities.User;

import java.util.List;

public interface UserService {
    void initRolesAndUser();

    User save(User user);

    User update(User user);

    String delete(String userName);

    UserRoleDto userRole(String userName);

    User forgotPassword(User user);

}
