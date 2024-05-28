package com.example.BanHang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BanHang.entity.BillItem;

public interface BillItemRepo extends JpaRepository<BillItem, Integer>{

}
