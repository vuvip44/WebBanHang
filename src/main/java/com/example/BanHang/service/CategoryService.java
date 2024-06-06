package com.example.BanHang.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BanHang.dto.CategoryDTO;
import com.example.BanHang.entity.Category;
import com.example.BanHang.repository.CategoryRepo;

import jakarta.persistence.NoResultException;

public interface CategoryService {
	void create(CategoryDTO categoryDTO);
	
	void update(CategoryDTO categoryDTO);
	
	void deleteById(int id);
	
	CategoryDTO searchByName(String name);
}
@Service
class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryRepo categoryRepo;
	
	@Override
	public void create(CategoryDTO categoryDTO) {
		// TODO Auto-generated method stub
		Category category=new ModelMapper().map(categoryDTO, Category.class);
		categoryRepo.save(category);
		
		categoryDTO.setId(category.getId());
	}

	@Override
	public void update(CategoryDTO categoryDTO) {
		// TODO Auto-generated method stub
		Category category=categoryRepo.findById(categoryDTO.getId()).orElseThrow(NoResultException::new);
		
		if(category!=null) {
			category.setName(categoryDTO.getName());
		}
		categoryRepo.save(category);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		categoryRepo.deleteById(id);
	}
	
	private CategoryDTO convertToDto(Category category) {
		return new ModelMapper().map(category, CategoryDTO.class);
	}
	
	@Override
	public CategoryDTO searchByName(String name) {
		// TODO Auto-generated method stub
		Category category=categoryRepo.searchByName(name);
		CategoryDTO categoryDTO=convertToDto(category);
		return categoryDTO;
	}
	
}
