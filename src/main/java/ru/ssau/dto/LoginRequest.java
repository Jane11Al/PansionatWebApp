package ru.ssau.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String fullname;
    private String password;
}