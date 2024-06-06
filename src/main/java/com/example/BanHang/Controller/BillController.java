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
import com.example.BanHang.dto.ResponseDTO;
import com.example.BanHang.dto.SearchDTO;
import com.example.BanHang.service.BillService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class BillController {
	@Autowired 
	BillService billService;
	
	@PostMapping("/")
	public ResponseDTO<BillDTO> create(@RequestBody @Valid BillDTO billDTO){
		billService.create(billDTO);
		return ResponseDTO.<BillDTO>builder().
				status(200).msg("ok").data(billDTO).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
		billService.delete(id);
		return ResponseDTO.<Void>builder().
				status(200).msg("ok").build();
	}
	
	@PutMapping("/")
	public ResponseDTO<BillDTO> update(@RequestBody @Valid BillDTO categoryDTO){
		billService.update(categoryDTO);
		return ResponseDTO.<BillDTO>builder().
				status(200).msg("ok").data(categoryDTO).build();
	}
	
	@GetMapping("/{id}")
	public ResponseDTO<BillDTO> getById(@RequestBody @Valid int id){
		BillDTO categoryDTO= billService.getById(id);
		return ResponseDTO.<BillDTO>builder().
				status(200).msg("ok").data(categoryDTO).build();
	}
	
	@GetMapping("/getAll")
	public ResponseDTO<PageDTO<BillDTO>> getAll(@RequestBody @Valid SearchDTO searchDTO){
		return ResponseDTO.<PageDTO<BillDTO>>builder().
				status(200).msg("ok").data(billService.getAll(searchDTO)).build();
	}
}
