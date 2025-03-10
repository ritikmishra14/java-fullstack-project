package com.exam.models;

import io.jsonwebtoken.Jwt;

public class JwtRequest {

    String username;
    String password;

    public JwtRequest(){}
    public JwtRequest(String username , String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
