package com.example.BanHang.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleDTO {
	private Integer id;

	// javax.validation
	@NotBlank
	
	private String name;
}