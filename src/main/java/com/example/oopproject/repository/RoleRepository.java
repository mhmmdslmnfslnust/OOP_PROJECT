package com.example.oopproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oopproject.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
