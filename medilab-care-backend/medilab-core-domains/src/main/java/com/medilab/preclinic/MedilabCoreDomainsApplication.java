package com.medilab.preclinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.medilab.preclinic.repo.PermissionRepo;
import com.medilab.preclinic.repo.RoleRepo;

@SpringBootApplication
public class MedilabCoreDomainsApplication {

	

	public static void main(String[] args) {
		SpringApplication.run(MedilabCoreDomainsApplication.class, args);
	}/*
		 * 
		 * @Override public void run(String... args) throws Exception {
		 * 
		 * UserPermission permssion = new UserPermission(); permssion.setName("view");
		 * permssion.setId(100);
		 * 
		 * permRepo.save(permssion);
		 * 
		 * 
		 * UserRole role = new UserRole(); role.setName("admin");
		 * 
		 * UserRoleToPermission roleToPermission = new UserRoleToPermission();
		 * roleToPermission.setUserPermission(permssion);
		 * roleToPermission.setUserRole(role);
		 * 
		 * Set<UserRoleToPermission> permissionsSet = new
		 * HashSet<UserRoleToPermission>(); permissionsSet.add(roleToPermission);
		 * 
		 * role.setPermissionsSet(permissionsSet);
		 * 
		 * manager.save(role);
		 * 
		 * }
		 */
}
