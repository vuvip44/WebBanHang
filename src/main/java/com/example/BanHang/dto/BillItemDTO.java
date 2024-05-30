package com.example.BanHang.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class BillItemDTO {
	private int id;
	
	@JsonIgnoreProperties("billItems")
	private BillDTO billDTO;
	
	private ProductDTO productDTO;
	
	@Min(0)
	private int quantity;
	
	@Min(0)
	private int price;
}
