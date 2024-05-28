package com.example.BanHang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BanHang.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{
	//Tim theo name
	Role searchByName(String name);
}
