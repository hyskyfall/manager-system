package com.example.ManagerSystem.dto;

import java.util.List;

public class AddUserCommand {
    private Long userId;
    private List<String> endpoint;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<String> getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(List<String> endpoint) {
        this.endpoint = endpoint;
    }
}
