package com.example.library.repository;

import com.example.library.domain.Role;
import com.example.library.domain.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {


    Optional<Role> findByType(RoleType roleType);
}
