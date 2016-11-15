package com.jh.share.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jh.share.model.Role;
public interface RoleRepository extends JpaRepository<Role, Long>{
}