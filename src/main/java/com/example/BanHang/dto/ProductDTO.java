package com.example.BanHang.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
	private int id;
	private String name;
	private String description;
	@Min(0)
	private double price;
	private int unitStock;
//	private List<String>  images;
	
	private CategoryDTO categoryDTO;
	
//	@JsonIgnore
//	private MultipartFile file;
	
	@JsonFormat(pattern ="dd/MM/yyyy HH:mm:ss")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date createAt;
	
}
