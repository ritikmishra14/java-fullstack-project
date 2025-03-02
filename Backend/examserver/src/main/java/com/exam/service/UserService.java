package com.exam.service;

import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserReposiroty userReposiroty;

    @Autowired
    private RoleRepository roleRepository;

    // create user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = this.userReposiroty.findByName(user.getUsername());
        if(local != null){
            throw new Exception("User Already Present");
        }
        else{
            // create user
            // add the userroles
            for(UserRole usr : userRoles){
                //add user role to role
               this.roleRepository.save(usr.getRole());
            }
            user.getUserRole().addAll(userRoles);
            local = this.userReposiroty.save(user);
        }
        return local;
    }

    // get user by name
    public User getUserByName(String username){
        User user = this.userReposiroty.findByName(username);
        return  user;
    }

    // update user
    public User updateUser(User user , Long userId){
        User previousUser = this.userReposiroty.findById(userId).get();
        System.out.println(previousUser);
        previousUser.setFirstName(user.getFirstName());
        previousUser.setUsername(user.getUsername());
        previousUser.setPhone(user.getPhone());
        previousUser.setEmail(user.getEmail());
        previousUser.setPassword(user.getPassword());
        this.userReposiroty.save(previousUser);
        return  previousUser;
    }

    // delete user
    public void deleteUser(Long userId){
        this.userReposiroty.deleteById(userId);
    }

    public List<User> getAllUsers() {
        List<User> users = this.userReposiroty.findAll();
        return users;
    }
}
