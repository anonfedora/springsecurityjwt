package com.anonfedora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anonfedora.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
