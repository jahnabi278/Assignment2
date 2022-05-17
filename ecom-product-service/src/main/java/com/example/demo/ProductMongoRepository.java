package com.example.demo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ProductDTO;

@Repository
public interface ProductMongoRepository extends MongoRepository<Product, String> {

	List<Product> findProductByProductName(String productName);

	void save(ProductDTO productDTO);

}
