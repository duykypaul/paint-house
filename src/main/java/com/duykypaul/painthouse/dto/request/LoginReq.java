package com.duykypaul.painthouse.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginReq {
    private String username;
    private String password;
}
