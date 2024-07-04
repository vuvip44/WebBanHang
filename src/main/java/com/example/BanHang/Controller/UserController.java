package com.example.BanHang.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BanHang.dto.ResponseDTO;
import com.example.BanHang.dto.UserDTO;
import com.example.BanHang.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/")
	public ResponseDTO<Void> create(@ModelAttribute @Valid UserDTO userDTO){
		userService.create(userDTO);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseDTO<Void> deleteById(@PathVariable("id") int id){
		userService.deleteById(id);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
}
