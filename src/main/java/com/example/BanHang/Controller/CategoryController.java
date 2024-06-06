package com.example.BanHang.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BanHang.dto.CategoryDTO;
import com.example.BanHang.dto.ResponseDTO;
import com.example.BanHang.service.CategoryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseDTO<CategoryDTO> create(@RequestBody @Valid CategoryDTO categoryDTO){
		categoryService.create(categoryDTO);
		return ResponseDTO.<CategoryDTO>builder().
				status(200).msg("ok").data(categoryDTO).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
		categoryService.deleteById(id);
		return ResponseDTO.<Void>builder().
				status(200).msg("ok").build();
	}
	
	@PutMapping("/")
	public ResponseDTO<CategoryDTO> update(@RequestBody @Valid CategoryDTO categoryDTO){
		categoryService.update(categoryDTO);
		return ResponseDTO.<CategoryDTO>builder().
				status(200).msg("ok").data(categoryDTO).build();
	}
	
	@GetMapping("/")
	public ResponseDTO<CategoryDTO> getById(@RequestBody @Valid String name){
		CategoryDTO categoryDTO= categoryService.searchByName(name);
		return ResponseDTO.<CategoryDTO>builder().
				status(200).msg("ok").data(categoryDTO).build();
	}
}	
