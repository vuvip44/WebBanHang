package com.example.BanHang.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.BanHang.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	//Tim theo username
	User findByUsername(String username);
	
	//Tim theo id
	User findById(int id);
	
	//Tim theo ngay sinh
	@Query("SELECT u FROM User u WHERE MONTH(u.birthdate)=:month AND DAY(u.birthdate)=:date")
	Page<User> searchByBirthDate(@Param("date") int date,@Param("month")int month,Pageable pageable);
	
	//Tim theo name
	@Query("SELECT u FROM User u WHERE u.name LIKE :x")
	Page<User> searchByName(@Param("x")String name,Pageable pageable);
	
	//Tim theo date
	@Query("SELECT u FROM User u WHERE u.createdAt >=:start")
	Page<User> searchByDate(@Param("start") Date start,Pageable pageable);
	
	//Xoa theo id
	void deleteById(int id);
	
	//Xoa theo username
	void deleteByUsername(String username);
}
