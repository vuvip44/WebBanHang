package com.example.BanHang.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ProductDTO {
	private int id;
	private String name;
	private String description;
	@Min(0)
	private double price;
	private int unitStock;
	private List<String>  images;
	
	private CategoryDTO categoryDTO;
	
	@JsonIgnore
	private MultipartFile file;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createAt;
	
}
