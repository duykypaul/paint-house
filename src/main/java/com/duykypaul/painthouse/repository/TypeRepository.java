package com.duykypaul.painthouse.repository;

import com.duykypaul.painthouse.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TypeRepository extends JpaRepository<Type, Long>, JpaSpecificationExecutor<Type> {

}