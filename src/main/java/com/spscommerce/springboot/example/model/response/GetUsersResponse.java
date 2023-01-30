package com.spscommerce.springboot.example.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spscommerce.springboot.example.model.User;

import java.util.List;

public class GetUsersResponse {

    @JsonProperty("users")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
