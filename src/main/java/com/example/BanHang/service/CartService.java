package com.example.BanHang.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BanHang.dto.CartDTO;
import com.example.BanHang.dto.CategoryDTO;
import com.example.BanHang.dto.ItemSelectedDTO;
import com.example.BanHang.dto.PageDTO;
import com.example.BanHang.dto.SearchDTO;
import com.example.BanHang.entity.Cart;
import com.example.BanHang.entity.ItemSelected;
import com.example.BanHang.entity.Product;
import com.example.BanHang.entity.User;
import com.example.BanHang.repository.CartRepo;
import com.example.BanHang.repository.ProductRepo;
import com.example.BanHang.repository.UserRepo;

import jakarta.persistence.NoResultException;

public interface CartService {
	void create(CartDTO cartDTO);
	
	void update(CartDTO cartDTO);
	
	void delete(int id);
	
	PageDTO<CartDTO> getAll(SearchDTO searchDTO);
}
@Service
class CartServiceImpl implements CartService{
	@Autowired
	CartRepo cartRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Override
	public void create(CartDTO cartDTO) {
		// TODO Auto-generated method stub
		User user=userRepo.findById(cartDTO.getUserDTO().getId());
		
		Cart cart=new Cart();
		cart.setUser(user);
		
		double totalPrice=0;
		
		List<ItemSelected> items=new ArrayList<ItemSelected>();
		for(ItemSelectedDTO itemSelectedDTO:cartDTO.getItems()) {
			ItemSelected item=new ItemSelected();
			
			Product product=productRepo.findById(itemSelectedDTO.getProductDTO().getId()).orElseThrow(NoResultException::new);
			
			item.setCart(cart);
			item.setProduct(product);
			item.setQuantity(itemSelectedDTO.getQuantity());
			item.setPrice(product.getPrice()*itemSelectedDTO.getQuantity());
			totalPrice+=product.getPrice()*itemSelectedDTO.getQuantity();
			items.add(item);
			
			
		}
		cart.setStatus("have not paid yet");
		cart.setTotalprice(totalPrice);
		cart.setItemSelecteds(items);
		cartRepo.save(cart);
	}

	@Override
	public void update(CartDTO cartDTO) {
		// TODO Auto-generated method stub
		Cart cart=cartRepo.findById(cartDTO.getId()).orElseThrow(NoResultException::new);
		List<ItemSelected> items=new ArrayList<ItemSelected>();
		
		for(ItemSelectedDTO itemSelectedDTO:cartDTO.getItems()) {
			ItemSelected item=new ItemSelected();
			
			Product product=productRepo.findById(itemSelectedDTO.getProductDTO().getId()).orElseThrow(NoResultException::new);
			
			
			item.setQuantity(itemSelectedDTO.getQuantity());
			item.setPrice(product.getPrice()*itemSelectedDTO.getQuantity());
			
			items.add(item);
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageDTO<CartDTO> getAll(SearchDTO searchDTO) {
		// TODO Auto-generated method stub
		return null;
	}}
