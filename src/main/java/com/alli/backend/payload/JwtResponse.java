package com.alli.backend.payload;

import com.alli.backend.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private String username;
    private String role;

    public JwtResponse(String accessToken, String id, String username, String role) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.role = role;
    }
}