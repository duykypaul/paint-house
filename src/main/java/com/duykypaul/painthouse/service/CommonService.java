package com.duykypaul.painthouse.service;

import com.duykypaul.painthouse.dto.UserDTO;
import com.duykypaul.painthouse.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class CommonService {
    final UserService userService;

    public final UserDTO getUser() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                String username = auth.getName();
                if (!username.equals("anonymousUser")) {
                    var entity = userService.findByUsername(username);
                    return UserMapper.INSTANCE.toDTO(entity);
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return null;
    }
}
