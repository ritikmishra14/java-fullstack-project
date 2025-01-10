package com.exam.controller;

import com.exam.models.Role;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // creating user
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {

        // Create Role

        //NORMAL ROLE
        Role normalRole = new Role();
        normalRole.setRoleId(1L);
        normalRole.setRoleName("NORMAL");
        //ADMIN ROLE
        Role adminRole = new Role();
        adminRole.setRoleId(2L);
        adminRole.setRoleName("ADMIN");
        // NORMAL+ADMIN ROLE
        Role normalAndAdmin = new Role();
        normalAndAdmin.setRoleId(3L);
        normalAndAdmin.setRoleName("NORMAL AND ADMIN");


        // create UserRole to add to set
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        if (user.getUsername().startsWith("r")) {
            userRole.setRole(adminRole);
        } else if (user.getUsername().length() > 4) {
            userRole.setRole(normalRole);
        } else {
            userRole.setRole(normalAndAdmin);
        }

        // create user role set
        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole);

        User createdUser = this.userService.createUser(user, roles);
        if (createdUser == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.of(Optional.of(createdUser));
        }
    }

    // Getting the user
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByName(@PathVariable("username") String username) {
        User user = this.userService.getUserByName(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long userId) {
        User updatedUser = this.userService.updateUser(user, userId);
        if (updatedUser == null) {
            throw new RuntimeException("User not present");
        }
        return ResponseEntity.ok(updatedUser);
    }

    // delete user
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
    }
}
