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
import com.example.BanHang.dto.RoleDTO;
import com.example.BanHang.dto.SearchDTO;
import com.example.BanHang.entity.Role;
import com.example.BanHang.repository.RoleRepo;

public interface RoleService {
	void create(RoleDTO roleDTO);
	
	void update(RoleDTO roleDTO);
	
	void delete(int id);
	
	PageDTO<RoleDTO> searchByName(SearchDTO searchDTO);
}

@Service
class RoleServiceImpl implements RoleService{
	@Autowired
	RoleRepo roleRepo;

	@Override
	public void create(RoleDTO roleDTO) {
		// TODO Auto-generated method stub
		Role role=new ModelMapper().map(roleDTO, Role.class);
		roleRepo.save(role);
	}

	@Override
	public void update(RoleDTO roleDTO) {
		// TODO Auto-generated method stub
		Role role=roleRepo.findById(roleDTO.getId()).orElse(null);
		if(role!=null) {
			role.setName(roleDTO.getName());
		}
		roleRepo.save(role);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		roleRepo.deleteById(id);
	}
	
	private RoleDTO convertToDto(Role role) {
		return new ModelMapper().map(role, RoleDTO.class);
	}
	
	@Override
	public PageDTO<RoleDTO> searchByName(SearchDTO searchDTO) {
		// TODO Auto-generated method stub
		Sort sort=Sort.by("id").ascending();
		
		if(StringUtils.hasText(searchDTO.getSortedField())) {
			sort=Sort.by(searchDTO.getSortedField()).ascending();
		}
		
		if(searchDTO.getCurrentPage()==null) {
			searchDTO.setCurrentPage(0);
		}
		
		if(searchDTO.getSize()==null) {
			searchDTO.setSize(5);
		}
		
		if(searchDTO.getKeyword()==null) {
			searchDTO.setKeyword("");
		}
		
		PageRequest pageRequest=PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getSize(),sort);
		
		Page<Role> page=roleRepo.searchByName(searchDTO.getKeyword(),pageRequest);
		
//		PageDTO<List<RoleDTO>> pageDTO=new PageDTO<List<RoleDTO>>();
//		
//		pageDTO.setTotalPages(page.getTotalPages());
//		
//		pageDTO.setTotalElements(page.getTotalElements());
//		
//		List<RoleDTO> roleDTOs=page.get().map(u->convertToDto(u)).collect(Collectors.toList());
//		
//		pageDTO.setData(roleDTOs);
//		return pageDTO;
		return PageDTO.<RoleDTO>builder().
				totalPages(page.getTotalPages()).
				totalElements(page.getTotalElements()).
				data(page.get().map(u->convertToDto(u)).collect(Collectors.toList())).build();
	}
	
	
}