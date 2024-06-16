package com.example.BanHang.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BanHang.dto.CartDTO;
import com.example.BanHang.dto.ResponseDTO;
import com.example.BanHang.service.CartService;
import com.example.BanHang.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartService cartService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/")
	public ResponseDTO<Void> add(@RequestBody @Valid CartDTO cartDTO){
		cartService.create(cartDTO);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
}
