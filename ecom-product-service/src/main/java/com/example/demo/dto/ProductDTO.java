package com.example.demo.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.Product;

@Document(collection = "products")
public class ProductDTO extends Product {
	@Id
	private String productId;

}
