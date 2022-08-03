package com.duykypaul.painthouse.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends BaseDTO{
    private String username;
    private String password;
    private String email;
}
