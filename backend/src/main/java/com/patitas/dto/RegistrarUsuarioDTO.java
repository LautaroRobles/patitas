package com.patitas.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter @Setter
public class RegistrarUsuarioDTO {
    private String username;
    private String password;
    private String email;
}
