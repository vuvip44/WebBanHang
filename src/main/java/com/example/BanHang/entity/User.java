package com.example.BanHang.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends TimeAuditable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String avatar;
	private String username;
	private String password;
	private String email;
	
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	
//	@ElementCollection(fetch = FetchType.EAGER)
//	@CollectionTable(name="user_role",
//	joinColumns = @JoinColumn(name="user_id"))
//	@Column(name="role")
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
	joinColumns = @JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name="role_id"))
	private List<Role> roles;
}
