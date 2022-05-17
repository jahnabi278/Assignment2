package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDTO;

@Service
public class ProductService {
	@Autowired
	ProductMongoRepository repo;

	public Result saveProduct(ProductDTO productDTO) {
		repo.save(productDTO);
		return new Result(200, ErrorMessages.SAVE_SUCCESSFUL);
	}

	public Optional<Product> getById(String id) {
		return repo.findById(id);
	}

	public List<Product> getByProductName(String productName) {
		return repo.findProductByProductName(productName);
	}

}
