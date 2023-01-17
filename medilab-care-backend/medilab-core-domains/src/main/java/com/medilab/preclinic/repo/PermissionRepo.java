package com.medilab.preclinic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medilab.preclinic.domain.UserPermission;

public interface PermissionRepo extends JpaRepository<UserPermission, Integer> {

}
