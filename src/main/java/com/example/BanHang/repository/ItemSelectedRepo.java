package com.example.BanHang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BanHang.entity.ItemSelected;
@Repository
public interface ItemSelectedRepo extends JpaRepository<ItemSelected, Integer>{

}
