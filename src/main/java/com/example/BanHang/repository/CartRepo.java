package com.example.BanHang.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.BanHang.entity.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{
	@Query("SELECT b FROM Cart b WHERE b.user.name LIKE :x")
	Page<Cart> searchByNameUser(@Param("x") String name,Pageable pageable);
	
	//Tim theo date
	@Query("SELECT b FROM Cart b WHERE b.createdAt >=:x")
	Page<Cart> searchByDate(@Param("x") Date date, Pageable pageable);
		
		//Thong ke don theo thang
//		@Query("SELECT count(b.id),month(b.createdAt),year(b.createdAt) FROM Bill b GROUP BY month(b.createdAt),year(b.createdAt")
//		List<Object[]> countBill();
		
	//Xoa theo id
	void deleteById(int id);
}
