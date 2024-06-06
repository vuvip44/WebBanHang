package com.example.BanHang.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BanHang.dto.BillDTO;
import com.example.BanHang.dto.PageDTO;
import com.example.BanHang.dto.ProductDTO;
import com.example.BanHang.dto.ResponseDTO;
import com.example.BanHang.dto.SearchDTO;
import com.example.BanHang.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@PostMapping("/")
	public ResponseDTO<ProductDTO> create(@RequestBody @Valid ProductDTO billDTO){
		productService.create(billDTO);
		return ResponseDTO.<ProductDTO>builder().
				status(200).msg("ok").data(billDTO).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
		productService.delete(id);
		return ResponseDTO.<Void>builder().
				status(200).msg("ok").build();
	}
	
	@PutMapping("/")
	public ResponseDTO<ProductDTO> update(@RequestBody @Valid ProductDTO categoryDTO){
		productService.update(categoryDTO);
		return ResponseDTO.<ProductDTO>builder().
				status(200).msg("ok").data(categoryDTO).build();
	}
	
	@GetMapping("/{id}")
	public ResponseDTO<ProductDTO> getById(@RequestBody @Valid int id){
		ProductDTO categoryDTO= productService.getById(id);
		return ResponseDTO.<ProductDTO>builder().
				status(200).msg("ok").data(categoryDTO).build();
	}
	
	@GetMapping("/getAll")
	public ResponseDTO<PageDTO<ProductDTO>> getAll(@RequestBody @Valid SearchDTO searchDTO){
		return ResponseDTO.<PageDTO<ProductDTO>>builder().
				status(200).msg("ok").data(productService.getAll(searchDTO)).build();
	}
	
	@GetMapping("/searchName")
	public ResponseDTO<PageDTO<ProductDTO>> searchName(@RequestBody @Valid SearchDTO searchDTO){
		return ResponseDTO.<PageDTO<ProductDTO>>builder().
				status(200).msg("ok").data(productService.searchByName(searchDTO)).build();
	}
}
