package com.duykypaul.painthouse.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class JwtDTO {
    private int code;
    private String status;
    private UserDTO user;
    private String token;
    private String type = "Bearer";

    public JwtDTO(HttpStatus httpStatus, String token, UserDTO user) {
        this.token = token;
        this.user = user;
        this.code = httpStatus.value();
        this.status = httpStatus.name();
    }
}
