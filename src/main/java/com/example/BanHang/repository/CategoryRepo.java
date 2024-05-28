package com.example.BanHang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BanHang.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
	//Tim theo name
	Category searchByName(String name);
	
	//Xoa theo id
	void deleteById(int id);
}
