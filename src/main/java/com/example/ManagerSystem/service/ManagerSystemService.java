package com.example.ManagerSystem.service;

import com.example.ManagerSystem.dto.AddUserCommand;
import com.example.ManagerSystem.dto.GetResourceQuery;
import com.example.ManagerSystem.dto.NoAccessException;
import com.example.ManagerSystem.dto.UserInfo;

public interface ManagerSystemService {
    void addUser(AddUserCommand command, UserInfo commandBy) throws NoAccessException;
    String getResource(GetResourceQuery query, UserInfo queryBy) throws NoAccessException;
}
