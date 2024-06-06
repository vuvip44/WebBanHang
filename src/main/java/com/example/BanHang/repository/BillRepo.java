package com.example.BanHang.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.BanHang.entity.Bill;

public interface BillRepo extends JpaRepository<Bill, Integer>{
	//Tim theo id
	Bill searchById(int id);
	
	//Tim theo user
	@Query("SELECT b FROM Bill b WHERE b.user.name LIKE :x")
	Page<Bill> searchByNameUser(@Param("x") String name,Pageable pageable);
	
	//Tim theo date
	@Query("SELECT b FROM Bill b WHERE b.createdAt >=:x")
	Page<Bill> searchByDate(@Param("x") Date date, Pageable pageable);
	
	//Thong ke don theo thang
//	@Query("SELECT count(b.id),month(b.createdAt),year(b.createdAt) FROM Bill b GROUP BY month(b.createdAt),year(b.createdAt")
//	List<Object[]> countBill();
	
	//Xoa theo id
	void deleteById(int id);
}
