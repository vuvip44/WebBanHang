package com.example.BanHang.service;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BanHang.dto.PageDTO;
import com.example.BanHang.dto.RoleDTO;
import com.example.BanHang.dto.SearchDTO;
import com.example.BanHang.entity.Role;
import com.example.BanHang.repository.RoleRepo;

import jakarta.persistence.NoResultException;

public interface RoleService {
	void create(RoleDTO roleDTO);
	
	void update(RoleDTO roleDTO);
	
	void delete(int id);
	
	RoleDTO getById(int id);
	
	
}

@Service
class RoleServiceImpl implements RoleService{
	@Autowired
	RoleRepo roleRepo;

	@Transactional
	public void create(RoleDTO roleDTO) {
		Role role = new ModelMapper().map(roleDTO, Role.class);
		roleRepo.save(role);
		// tra ve idsau khi tao
		roleDTO.setId(role.getId());
	}

	@Transactional
	public void update(RoleDTO roleDTO) {
		Role role = roleRepo.findById(roleDTO.getId()).orElseThrow(jakarta.persistence.NoResultException::new);
		role.setName(roleDTO.getName());

		roleRepo.save(role);
	}

	@Transactional
	public void delete(int id) {
		roleRepo.deleteById(id);
	}

	public PageDTO<RoleDTO> search(SearchDTO searchDTO) {
		Pageable pageable = PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getSize());

		Page<Role> pageRS = roleRepo.searchByName(searchDTO.getKeyword(), pageable);

		return PageDTO.<RoleDTO>builder()
				.totalPages(pageRS.getTotalPages())
				.totalElements(pageRS.getTotalElements())
				.data(pageRS.get()
						.map(r -> convert(r)).collect(Collectors.toList()))
				.build();
	}

	private RoleDTO convert(Role role) {
		return new ModelMapper().map(role, RoleDTO.class);
	}

	@Override
	public RoleDTO getById(int id) {
		// TODO Auto-generated method stub
		Role role=roleRepo.findById(id).orElseThrow(NoResultException::new);
		return new ModelMapper().map(role, RoleDTO.class);
	}
	
	
}