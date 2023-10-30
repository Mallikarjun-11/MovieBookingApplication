package com.moviebookingapp.user.controllers;

import com.moviebookingapp.user.dto.UserRoleDto;
import com.moviebookingapp.user.entities.JwtRequest;
import com.moviebookingapp.user.entities.JwtResponse;
import com.moviebookingapp.user.entities.Role;
import com.moviebookingapp.user.entities.User;
import com.moviebookingapp.user.service.JwtService;
import com.moviebookingapp.user.service.UserService;
import com.moviebookingapp.user.service.impl.RoleServiceImpl;
import com.moviebookingapp.user.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;


@RequestMapping("/api/v1/moviebooking")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private JwtService jwtService;




    @PostConstruct
    public void initRolesandUsers(){
        userService.initRolesAndUser();
    }



    @PostMapping("/login")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }


    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        User response=userService.save(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/forgotPassword")
    public ResponseEntity<User> updatePassword(@RequestBody User user){
        User response=userService.forgotPassword(user);
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUserDetails(@RequestBody User user){
        User response=userService.update(user);
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @DeleteMapping("/deleteUser/{userName}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> deleteUser(@PathVariable String userName){
        String response= userService.delete(userName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }




    @GetMapping({"/userRole/{userName}"})
    public ResponseEntity<UserRoleDto> getRoles(@PathVariable String userName ){


        System.out.println("--------------------------------------------------------------------");

        UserRoleDto response= userService.userRole(userName);

        return new ResponseEntity<>(response,HttpStatus.OK);

    }



}
