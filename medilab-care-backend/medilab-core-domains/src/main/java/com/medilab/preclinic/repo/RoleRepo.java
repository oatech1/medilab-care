package com.medilab.preclinic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medilab.preclinic.domain.UserRole;

public interface RoleRepo extends JpaRepository<UserRole, Integer> {

}
