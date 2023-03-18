package com.company.tree.Entity.Request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;


}