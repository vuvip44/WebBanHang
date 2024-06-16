package com.example.BanHang.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	
	
//	private String images;
	
	@ManyToOne
	private Category category;
}
//1 product co nhieu anh
//@ElementCollection(fetch=FetchType.EAGER)
//@CollectionTable(name="product_images",
//joinColumns = @JoinColumn(name="product_id"))
//@Column(name="image")
