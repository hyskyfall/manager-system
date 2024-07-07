package com.example.ManagerSystem.repository;


import com.example.ManagerSystem.dto.AddUserCommand;
import com.example.ManagerSystem.dto.GetResourceQuery;
import com.example.ManagerSystem.dto.UserInfo;

public interface ManagerSystemRepository {
    void addUser(AddUserCommand command);

    String getResource(GetResourceQuery query, UserInfo queryBy);
}
