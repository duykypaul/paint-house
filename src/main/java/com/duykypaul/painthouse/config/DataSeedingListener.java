package com.duykypaul.painthouse.config;

import com.duykypaul.painthouse.common.Constant;
import com.duykypaul.painthouse.model.Category;
import com.duykypaul.painthouse.model.Product;
import com.duykypaul.painthouse.model.Role;
import com.duykypaul.painthouse.model.User;
import com.duykypaul.painthouse.repository.CategoryRepository;
import com.duykypaul.painthouse.repository.ProductRepository;
import com.duykypaul.painthouse.repository.RoleRepository;
import com.duykypaul.painthouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class DataSeedingListener {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //Add Roles
        if (roleRepository.findByName(Constant.Auth.ROLE.ROLE_ADMIN).isEmpty()) {
            roleRepository.save(new Role(Constant.Auth.ROLE.ROLE_ADMIN));
        }
        if (roleRepository.findByName(Constant.Auth.ROLE.ROLE_MODERATOR).isEmpty()) {
            roleRepository.save(new Role(Constant.Auth.ROLE.ROLE_MODERATOR));
        }
        if (roleRepository.findByName(Constant.Auth.ROLE.ROLE_USER).isEmpty()) {
            roleRepository.save(new Role(Constant.Auth.ROLE.ROLE_USER));
        }
        // Admin account
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setEmail(Constant.Auth.ADMIN_EMAIL);
            admin.setPassword(passwordEncoder.encode(Constant.Auth.ADMIN_PASSWORD));
            admin.setUsername(Constant.Auth.ADMIN_NAME);
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName(Constant.Auth.ROLE.ROLE_ADMIN).get());
            roles.add(roleRepository.findByName(Constant.Auth.ROLE.ROLE_MODERATOR).get());
            roles.add(roleRepository.findByName(Constant.Auth.ROLE.ROLE_USER).get());
            admin.setEnabled(true);
            admin.setRoles(roles);
            userRepository.save(admin);
        }

        if (categoryRepository.count() == 0) {
            categoryRepository.save(Category.builder().name("Sơn nội thất").build());
        }

        if (productRepository.count() == 0) {
            productRepository.save(Product.builder()
                    .categoryId(1L)
                    .name("KS01 – SƠN MEN SỨ NỘI THẤT SILVER")
                    .metaCode("KS01").metaType("MULTI-PUR PRIMER")
                    .packing("5,10")
                    .build()
            );
        }
    }
}
