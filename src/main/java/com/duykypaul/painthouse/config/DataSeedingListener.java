package com.duykypaul.painthouse.config;


import com.duykypaul.painthouse.common.Constant;
import com.duykypaul.painthouse.model.Role;
import com.duykypaul.painthouse.model.User;
import com.duykypaul.painthouse.repository.RoleRepository;
import com.duykypaul.painthouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // Add Roles
        if (!roleRepository.findByName(Constant.AUTH.ROLE.ROLE_ADMIN).isPresent()) {
            roleRepository.save(new Role(Constant.AUTH.ROLE.ROLE_ADMIN));
        }
        if (!roleRepository.findByName(Constant.AUTH.ROLE.ROLE_MODERATOR).isPresent()) {
            roleRepository.save(new Role(Constant.AUTH.ROLE.ROLE_MODERATOR));
        }
        if (!roleRepository.findByName(Constant.AUTH.ROLE.ROLE_USER).isPresent()) {
            roleRepository.save(new Role(Constant.AUTH.ROLE.ROLE_USER));
        }
        // Admin account
        if (!userRepository.findByEmail(Constant.AUTH.ADMIN_EMAIL).isPresent()) {
            User admin = new User();
            admin.setEmail(Constant.AUTH.ADMIN_EMAIL);
            admin.setPassword(passwordEncoder.encode(Constant.AUTH.ADMIN_PASSWORD));
            admin.setUsername(Constant.AUTH.ADMIN_NAME);
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName(Constant.AUTH.ROLE.ROLE_ADMIN).get());
            roles.add(roleRepository.findByName(Constant.AUTH.ROLE.ROLE_MODERATOR).get());
            roles.add(roleRepository.findByName(Constant.AUTH.ROLE.ROLE_USER).get());
            admin.setRoles(roles);
            userRepository.save(admin);
        }
    }
}
