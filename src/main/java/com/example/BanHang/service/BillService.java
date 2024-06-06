package com.example.BanHang.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.BanHang.dto.BillDTO;
import com.example.BanHang.dto.BillItemDTO;
import com.example.BanHang.dto.PageDTO;
import com.example.BanHang.dto.SearchDTO;
import com.example.BanHang.entity.Bill;
import com.example.BanHang.entity.BillItem;
import com.example.BanHang.entity.Product;
import com.example.BanHang.entity.User;
import com.example.BanHang.repository.BillRepo;
import com.example.BanHang.repository.ProductRepo;
import com.example.BanHang.repository.UserRepo;

import jakarta.persistence.NoResultException;

public interface BillService {
	void create(BillDTO billDTO);
	
	void update(BillDTO billDTO);
	
	void delete(int id);
	
	BillDTO getById(int id);
	
	PageDTO<BillDTO> getAll(SearchDTO searchDTO);
}

@Service
class BillSericeImpl implements BillService{
	@Autowired 
	BillRepo billRepo;
	
	@Autowired 
	UserRepo userRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Override
	public void create(BillDTO billDTO) {
		// TODO Auto-generated method stub
		User user=userRepo.findById(billDTO.getUserDTO().getId());
		
		Bill bill=new Bill();
		bill.setUser(user);
		
		List<BillItem> billItems=new ArrayList<>();
		for(BillItemDTO billItemDTO:billDTO.getBillItems()) {
			BillItem billItem=new BillItem();
			
			Product product=productRepo.findById(billItemDTO.getProductDTO().getId()).orElseThrow(NoResultException::new);
			
			billItem.setBill(bill);
			billItem.setQuantity(billItemDTO.getQuantity());
			billItem.setPrice(product.getPrice()*billItemDTO.getQuantity());
			
			product.setUnitStock(product.getUnitStock()-billItemDTO.getQuantity());
			productRepo.save(product);
			
			billItems.add(billItem);
		}
		bill.setBillItems(billItems);
		billRepo.save(bill);
	}
	@Override
	public void update(BillDTO billDTO) {
		// TODO Auto-generated method stub
		User user=userRepo.findById(billDTO.getUserDTO().getId());
		
		Bill bill=billRepo.findById(billDTO.getId()).orElseThrow(NoResultException::new);
		
		bill.setUser(user);
		billRepo.save(bill);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		billRepo.deleteById(id);
	}

	@Override
	public BillDTO getById(int id) {
		// TODO Auto-generated method stub
		Bill bill=billRepo.findById(id).orElseThrow(NoResultException::new);
		
		return new ModelMapper().map(bill, BillDTO.class);
	}
	@Override
	public PageDTO<BillDTO> getAll(SearchDTO searchDTO) {
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
		Page<Bill> page=billRepo.findAll(pageRequest);
		
		return PageDTO.<BillDTO>builder().
				totalPages(page.getTotalPages()).
				totalElements(page.getTotalElements()).
				data(page.get().map(u->new ModelMapper().map(u, BillDTO.class)).collect(Collectors.toList())).build();
		
	}
	
}
