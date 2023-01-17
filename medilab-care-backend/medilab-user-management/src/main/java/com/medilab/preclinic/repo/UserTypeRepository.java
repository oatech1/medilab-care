package com.medilab.preclinic.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medilab.preclinic.domain.MedilabUserType;

public interface UserTypeRepository extends JpaRepository<MedilabUserType, Integer> {

}
