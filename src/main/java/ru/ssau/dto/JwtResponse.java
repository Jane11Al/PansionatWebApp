package ru.ssau.dto;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String fullName;
    private String role;

    public JwtResponse(String token, Long id, String fullName, String role) {
        this.token = token;
        this.id = id;
        this.fullName = fullName;
        this.role = role;
    }
}