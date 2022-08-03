package com.duykypaul.painthouse.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDTO {
    private int status;
    private UserDTO user;
    private String token;
    private String type = "Bearer";

    public JwtDTO(int status, String token, UserDTO user) {
        this.token = token;
        this.user = user;
        this.status = status;
    }
}
