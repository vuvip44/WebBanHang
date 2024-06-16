package com.example.BanHang.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ItemSelectedDTO {
	private int id;
	
	@JsonIgnoreProperties("items")
	private CartDTO cartDTO;
	
	private ProductDTO productDTO;
	
	@Min(0)
	private int quantity;
	
	@Min(0)
	private int price;
}
