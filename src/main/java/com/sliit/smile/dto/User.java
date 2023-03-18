package com.sliit.smile.dto;

import com.sliit.smile.enumz.Role;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;

    private String username;

    private String password;

    private String name;

    private LocalDateTime createTime;
    private Role role;

    private String token;
}

