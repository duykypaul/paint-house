package com.duykypaul.painthouse.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class LoginReq {
    @NotBlank
    @Size(max = 20, min = 3)
    private String username;
    @NotBlank
    @Size(max = 120, min = 6)
    private String password;
}
