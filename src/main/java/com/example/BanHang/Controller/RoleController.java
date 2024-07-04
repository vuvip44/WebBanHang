package com.example.BanHang.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BanHang.dto.PageDTO;
import com.example.BanHang.dto.ResponseDTO;
import com.example.BanHang.dto.RoleDTO;
import com.example.BanHang.dto.SearchDTO;
import com.example.BanHang.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/role")
public class RoleController {
	@Autowired // DI : dependency inject
	RoleService roleService;

	@PostMapping("/")
	public ResponseDTO<Void> create(
			@RequestBody @Valid RoleDTO roleDTO) {
		roleService.create(roleDTO);
		return ResponseDTO.<Void>builder().status(200)
				.msg("ok").build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
		roleService.delete(id);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
	
	@PutMapping("/")
	public ResponseDTO<RoleDTO> edit(@RequestBody @Valid RoleDTO roleDTO){
		roleService.update(roleDTO);
		return ResponseDTO.<RoleDTO>builder().status(200).msg("ok").data(roleDTO).build();
	}
	
	@GetMapping("/{id}")
	public ResponseDTO<RoleDTO> getById(@PathVariable("id") int id){
		return ResponseDTO.<RoleDTO>builder().status(200).msg("ok").data(roleService.getById(id)).build();
	}
}
