package com.exam.service;

import com.exam.models.Role;
import com.exam.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    // autowired not needed for constructor type autowiring.

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
}
