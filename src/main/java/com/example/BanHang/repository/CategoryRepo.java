package com.example.BanHang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.BanHang.dto.CategoryDTO;
import com.example.BanHang.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{
	
	//Tim theo name
	@Query("SELECT c FROM Category c WHERE c.name LIKE :x")
	Category searchByName(@Param("x") String name);
	
	//Xoa theo id
	void deleteById(int id);
}
