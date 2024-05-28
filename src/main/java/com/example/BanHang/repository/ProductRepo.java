package com.example.BanHang.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.BanHang.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
	//Tim theo ten
	@Query("SELECT p FROM Product p WHERE p.name LIKE :x")
	Page<Product> searchByName(@Param("x")String name,Pageable pageable);
	
	//Tim theo category
	@Query("SELECT p FROM Product p WHERE p.category.name LIKE :x")
	Page<Product> searchByCategory(@Param("x")String category,Pageable pageable);
	
	//Tim theo gia
	@Query("SELECT p FROM Product p WHERE p.price >=start AND p.price <=end")
	Page<Product> searchByPrice(@Param("start") double start,@Param("end") double end,Pageable pageable);
	
	//Xoa theo id
	void deleteById(int id);
}
