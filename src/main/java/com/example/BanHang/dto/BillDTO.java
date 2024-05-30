package com.example.BanHang.dto;

import java.util.List;

import lombok.Data;

@Data
public class BillDTO {
	private int id;
	private String status;
	private UserDTO userDTO;
	
	
	private List<BillItemDTO> billItems;
}
