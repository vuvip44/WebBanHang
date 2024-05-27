package com.example.BanHang.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends TimeAuditable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	private String description;
	private double price;
	private int unitStock;
	
	//1 product co nhieu anh
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="product_images",
	joinColumns = @JoinColumn(name="product_id"))
	@Column(name="image")
	private List<String> images;
	
	@ManyToOne
	private Category category;
}
