package com.example.BanHang.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartDTO {
	private int id;
	private String status;
	private UserDTO userDTO;
	
	private List<ItemSelectedDTO> items;
	private double totalprice;
}
