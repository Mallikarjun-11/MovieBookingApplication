package com.moviebookingapp.user.service.impl;


import com.moviebookingapp.user.dto.UserRoleDto;
import com.moviebookingapp.user.entities.Movie;

import com.moviebookingapp.user.entities.Role;

import com.moviebookingapp.user.entities.User;

import com.moviebookingapp.user.exception.InvalidDetailsException;

import com.moviebookingapp.user.exception.PasswordViolationException;
import com.moviebookingapp.user.exception.ResourceNotFoundException;

import com.moviebookingapp.user.repositories.MovieRepository;

import com.moviebookingapp.user.repositories.RoleRepository;

import com.moviebookingapp.user.repositories.UserRepository;

import com.moviebookingapp.user.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;


import java.util.HashSet;

import java.util.List;

import java.util.NoSuchElementException;

import java.util.Set;



@Service
public class UserServiceImpl implements UserService {


    @Autowired

    private UserRepository userRepository;


    @Autowired

    private RoleRepository roleRepository;


    @Autowired

    private PasswordEncoder passwordEncoder;



    @Override

    public void initRolesAndUser(){

        Role adminRole = new Role();

        adminRole.setRoleName("Admin");

        adminRole.setRoleDescription("Admin role");

        roleRepository.save(adminRole);


        Role userRole = new Role();

        userRole.setRoleName("User");

        userRole.setRoleDescription("Default role for newly created record");

        roleRepository.save(userRole);


        User adminUser=new User();

        adminUser.setUserId(1L);

        adminUser.setFirstName("admin");

        adminUser.setLastName("admin");

        adminUser.setUserName("admin");

        adminUser.setPassword(getEncodedPassword("admin"));

        adminUser.setConfirmPassword(getEncodedPassword("admin"));

// adminUser.setEmail("admin@gmail.com");

        adminUser.setEmailId("admin@gmail.com");

        adminUser.setContactNumber("7674975126");

        Set<Role> adminRoles=new HashSet<>();

        adminRoles.add(adminRole);

        adminUser.setRoles(adminRoles);

        userRepository.save(adminUser);



// User testUser=new User();

// testUser.setUserId(2L);

// testUser.setFirstName("test");

// testUser.setLastName("test");

// testUser.setUserName("test");

// testUser.setPassword(getEncodedPassword("test"));

// testUser.setConfirmPassword(getEncodedPassword("test"));

// testUser.setEmail("test@gmail.com");

// testUser.setContactNumber("1234567890");

// Set<Role> userRoles=new HashSet<>();

// userRoles.add(userRole);

// testUser.setRoles(userRoles);

// userRepository.save(testUser);

    }



    @Override

    public User save(User user) {


        Role role = roleRepository.findById("User").get();

        Set<Role> userRoles = new HashSet<>();

        userRoles.add(role);

        user.setRoles(userRoles);


        String pass=user.getPassword();

        String confirmPass=user.getConfirmPassword();



        if(!pass.equals(confirmPass)){

            throw new PasswordViolationException("Password and Confirm Password does not match");
        }


        user.setPassword(getEncodedPassword(user.getPassword()));

        user.setConfirmPassword(getEncodedPassword(user.getConfirmPassword()));


        User savedUser= userRepository.save(user);




        return savedUser;

    }


    @Override
    public User forgotPassword(User user) {

        User updateUser = userRepository.findByUserName(user.getUserName()).orElseThrow(

                ()-> new ResourceNotFoundException("User", "user name",user.getUserName())

        );

        if(!updateUser.getFirstName().equals(user.getFirstName()) || !updateUser.getLastName().equals(user.getLastName()) || !updateUser.getEmailId().equals(user.getEmailId() ) || !updateUser.getContactNumber().equals(user.getContactNumber()) ){
            throw new InvalidDetailsException("Please enter the correct details");
        }else if(!user.getPassword().equals(user.getConfirmPassword())){
            throw new PasswordViolationException("Password and Confirm password are not same");
        }else {


            System.out.println("--------------------------------------------- inside else block");


            updateUser.setPassword(getEncodedPassword(user.getPassword()));

            updateUser.setConfirmPassword(getEncodedPassword(user.getConfirmPassword()));

            userRepository.save(updateUser);

            return updateUser;

        }


    }

    @Override
    public String delete(String userName) {
        User user=userRepository.findByUserName(userName).orElseThrow(
                ()->new ResourceNotFoundException("User","user name",userName)
        );
        userRepository.delete(user);
        String resp="User "+userName+" deleted successfully";
        return resp;
    }


    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);

    }




    @Override
    public UserRoleDto userRole(String userName) {

        User user = userRepository.findByUserName(userName).orElseThrow(

                () -> new ResourceNotFoundException("User", "user name", userName)

        );


        UserRoleDto userRoleDto = new UserRoleDto();

        Set<String> roles=new HashSet<>();


        for(Role s:user.getRoles()){

            roles.add(s.getRoleName());

        }


        userRoleDto.setUserName(userName);

        userRoleDto.setRoles(roles);



        return userRoleDto;

    }


    @Override
    public User update(User user) {

        User updateUser = userRepository.findByUserName(user.getUserName()).orElseThrow(

                ()-> new ResourceNotFoundException("User", "user name",user.getUserName())

        );

        if(user.getFirstName()==null || user.getFirstName().isEmpty() || user.getLastName()==null || user.getLastName().isEmpty() || user.getEmailId()==null || user.getEmailId().isEmpty() || user.getContactNumber()==null || user.getContactNumber().isEmpty() ){
            throw new InvalidDetailsException("Please enter the correct details");
        }else if(!user.getPassword().equals(user.getConfirmPassword())){
            throw new PasswordViolationException("Password and Confirm password are not same");
        }else {


            updateUser.setEmailId(user.getEmailId());
            updateUser.setContactNumber(user.getContactNumber());
            updateUser.setFirstName(user.getFirstName());
            updateUser.setLastName(user.getLastName());
            updateUser.setPassword(getEncodedPassword(user.getPassword()));
            updateUser.setConfirmPassword(getEncodedPassword(user.getConfirmPassword()));

            userRepository.save(updateUser);

            return updateUser;

        }


    }




}


//package com.moviebookingapp.user.service.impl;
//
//import com.moviebookingapp.user.entities.Movie;
//import com.moviebookingapp.user.entities.Role;
//import com.moviebookingapp.user.entities.User;
//import com.moviebookingapp.user.repositories.MovieRepository;
//import com.moviebookingapp.user.repositories.RoleRepository;
//import com.moviebookingapp.user.repositories.UserRepository;
//import com.moviebookingapp.user.service.UserService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//    @Override
//    public void initRolesAndUser(){
//        Role adminRole = new Role();
//        adminRole.setRoleName("Admin");
//        adminRole.setRoleDescription("Admin role");
//        roleRepository.save(adminRole);
//
//        Role userRole = new Role();
//        userRole.setRoleName("User");
//        userRole.setRoleDescription("Default role for newly created record");
//        roleRepository.save(userRole);
//
//        User adminUser=new User();
//        adminUser.setUserId(1L);
//        adminUser.setFirstName("admin");
//        adminUser.setLastName("admin");
//        adminUser.setUserName("admin");
//        adminUser.setPassword(getEncodedPassword("admin"));
//        adminUser.setConfirmPassword(getEncodedPassword("admin"));
//        adminUser.setEmail("admin@gmail.com");
//        adminUser.setContactNumber("7674975126");
//        Set<Role> adminRoles=new HashSet<>();
//        adminRoles.add(adminRole);
//        adminUser.setRoles(adminRoles);
//        userRepository.save(adminUser);
//
//
////        User testUser=new User();
////        testUser.setUserId(2L);
////        testUser.setFirstName("test");
////        testUser.setLastName("test");
////        testUser.setUserName("test");
////        testUser.setPassword(getEncodedPassword("test"));
////        testUser.setConfirmPassword(getEncodedPassword("test"));
////        testUser.setEmail("test@gmail.com");
////        testUser.setContactNumber("1234567890");
////        Set<Role> userRoles=new HashSet<>();
////        userRoles.add(userRole);
////        testUser.setRoles(userRoles);
////        userRepository.save(testUser);
//    }
//
//
//    @Override
//    public User save(User user) {
//
//        Role role = roleRepository.findById("User").get();
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(role);
//        user.setRoles(userRoles);
//
//        user.setPassword(getEncodedPassword(user.getPassword()));
//        user.setConfirmPassword(getEncodedPassword(user.getConfirmPassword()));
//
//        User savedUser= userRepository.save(user);
//        return savedUser;
//    }
//
//    @Override
//    public User update(User user) {
//        User updateUser=userRepository.findByUserName(user.getUserName()).get();
//
//        updateUser.setPassword(getEncodedPassword(user.getPassword()));
//        updateUser.setConfirmPassword(getEncodedPassword(user.getConfirmPassword()));
//
//
//        return null;
//    }
//
//    public String getEncodedPassword(String password){
//        return passwordEncoder.encode(password);
//    }
//
//
//
//}
