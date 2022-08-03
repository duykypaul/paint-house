package com.duykypaul.painthouse.service;

import com.duykypaul.painthouse.model.User;
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

    public final User getUser() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                String username = auth.getName();
                if(!username.equals("anonymousUser")) {
                    return userService.findByUsername(username);
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return null;
    }
}
