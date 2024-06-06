package com.example.BanHang.service;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.BanHang.dto.PageDTO;
import com.example.BanHang.dto.ProductDTO;
import com.example.BanHang.dto.SearchDTO;
import com.example.BanHang.entity.Category;
import com.example.BanHang.entity.Product;
import com.example.BanHang.repository.CategoryRepo;
import com.example.BanHang.repository.ProductRepo;

import jakarta.persistence.NoResultException;

public interface ProductService {
	void create(ProductDTO productDTO);
	
	void update(ProductDTO productDTO);
	
	void delete(int id);
	
	ProductDTO getById(int id);
	
	PageDTO<ProductDTO> searchByName(SearchDTO searchDTO);
	
	PageDTO<ProductDTO> getAll(SearchDTO searchDTO);
}

@Service
class ProductServiceImpl implements ProductService{
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Override
	public void create(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		
		
		Category category=categoryRepo.findById(productDTO.getCategoryDTO().getId()).orElse(null);
		
		Product product=new ModelMapper().map(productDTO, Product.class);
		product.setCategory(category);
		productRepo.save(product);
		productDTO.setId(product.getId());
	}

	@Override
	public void update(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		Product product=productRepo.findById(productDTO.getId()).orElseThrow(NoResultException::new);
		
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setUnitStock(productDTO.getUnitStock());
		
		productRepo.save(product);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		productRepo.deleteById(id);
	}
	
	public ProductDTO convert(Product product) {
		return new ModelMapper().map(product, ProductDTO.class);
	}

	@Override
	public ProductDTO getById(int id) {
		// TODO Auto-generated method stub
		Product product=productRepo.findById(id).orElseThrow(NoResultException::new);
		
		ProductDTO productDTO=convert(product);
		return productDTO;
	}

	@Override
	public PageDTO<ProductDTO> searchByName(SearchDTO searchDTO) {
		// TODO Auto-generated method stub
		Sort sortBy=Sort.by("id").ascending();
		
		if(StringUtils.hasText(searchDTO.getSortedField())) {
			sortBy=Sort.by(searchDTO.getSortedField()).ascending();
		}
		
		if(searchDTO.getCurrentPage()==null) {
			searchDTO.setCurrentPage(0);
		}
		
		if(searchDTO.getSize()==null) {
			searchDTO.setSize(0);
		}
		
		if(searchDTO.getKeyword()==null) {
			searchDTO.setKeyword("");
		}
		
		PageRequest pageRequest=PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getSize(),sortBy);
		
		Category category=categoryRepo.searchByName(searchDTO.getKeyword());
		if(category!=null) {
			Page<Product> page=productRepo.searchByCategory(searchDTO.getKeyword(), pageRequest);
			return PageDTO.<ProductDTO>builder().
					totalElements(page.getTotalElements()).
					totalPages(page.getTotalPages()).
					data(page.get().map(u->convert(u)).collect(Collectors.toList())).build();
		}
		Page<Product> page=productRepo.searchByName(searchDTO.getKeyword(), pageRequest);
		return PageDTO.<ProductDTO>builder().
				totalElements(page.getTotalElements()).
				totalPages(page.getTotalPages()).
				data(page.get().map(u->convert(u)).collect(Collectors.toList())).build();
	}

	@Override
	public PageDTO<ProductDTO> getAll(SearchDTO searchDTO) {
		// TODO Auto-generated method stub
		Sort sortBy=Sort.by("id").ascending();
		
		if(StringUtils.hasText(searchDTO.getSortedField())) {
			sortBy=Sort.by(searchDTO.getSortedField()).ascending();
		}
		
		if(searchDTO.getCurrentPage()==null) {
			searchDTO.setCurrentPage(0);
		}
		
		if(searchDTO.getSize()==null) {
			searchDTO.setSize(0);
		}
		
		if(searchDTO.getKeyword()==null) {
			searchDTO.setKeyword("");
		}
		
		PageRequest pageRequest=PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getSize(),sortBy);
		Page<Product> page=productRepo.findAll(pageRequest);
		return PageDTO.<ProductDTO>builder().
				totalPages(page.getTotalPages()).
				totalElements(page.getTotalElements()).
				data(page.get().map(u->convert(u)).collect(Collectors.toList())).build();
		
	}
	
}
