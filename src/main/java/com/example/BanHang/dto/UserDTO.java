package com.example.BanHang.dto;

import java.io.File;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserDTO {
	
	private int id;
	private String name;
	private String avatar;
	private String username;
	private String password;
	private String email;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthdate;
	
	@JsonIgnore
	private File file;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createAt;
	
	
	
	
	private RoleDTO roles;
}
