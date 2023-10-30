package com.moviebookingapp.user.service.impl;


import com.moviebookingapp.user.entities.Role;
import com.moviebookingapp.user.repositories.RoleRepository;
import com.moviebookingapp.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
    public class RoleServiceImpl implements RoleService {

        @Autowired
        private RoleRepository roleRepository;

        public Role createNewRole(Role role) {
            return roleRepository.save(role);
        }
    }

