package com.example.BanHang.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.BanHang.dto.PageDTO;
import com.example.BanHang.dto.SearchDTO;
import com.example.BanHang.dto.UserDTO;
import com.example.BanHang.entity.User;
import com.example.BanHang.repository.UserRepo;

public interface UserService {
	void create(UserDTO userDTO);
	
	void update(UserDTO userDTO);
	
	List<UserDTO> getAll();
	
	UserDTO getById(int id);
	
	PageDTO<List<UserDTO>> searchByName(SearchDTO searchDTO);
	
	void deleteById(int id);
}

@Service
class UserServiceImpl implements UserService{
	@Autowired
	UserRepo userRepo;
	
	@Override
	public void create(UserDTO userDTO) {
		// TODO Auto-generated method stub
		User user=new ModelMapper().map(userDTO, User.class);
		userRepo.save(user);
	}

	@Override
	public void update(UserDTO user) {
		// TODO Auto-generated method stub
		
	}
	
	private UserDTO convertToDto(User user) {
		return new ModelMapper().map(user, UserDTO.class);
	}
	
	@Override
	public List<UserDTO> getAll() {
		// TODO Auto-generated method stub
		List<User> users=userRepo.findAll();
		return users.stream().map(u->convertToDto(u)).collect(Collectors.toList());
		
	}

	@Override
	public UserDTO getById(int id) {
		// TODO Auto-generated method stub
		User user=userRepo.findById(id);
		if(user!=null) {
			return convertToDto(user);
		}
		return null;
	}

	@Override
	public PageDTO<List<UserDTO>> searchByName(SearchDTO searchDTO) {
		// TODO Auto-generated method stub
		Sort sortBy=Sort.by("id").ascending();
		
		if(StringUtils.hasText(searchDTO.getSortedField())) {}
		return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		userRepo.deleteById(id);
	}
	
}