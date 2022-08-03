package com.duykypaul.painthouse.repository;

import com.duykypaul.painthouse.common.Constant;
import com.duykypaul.painthouse.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Constant.AUTH.ROLE name);
}
