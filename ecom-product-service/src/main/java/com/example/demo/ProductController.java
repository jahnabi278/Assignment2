package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/ecom")
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	RestTemplate restTemplate;

	@PostMapping
	public Result createProduct(@RequestBody Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setActive(product.getisActive());
		productDTO.setPrice(product.getPrice());
		productDTO.setProductBigImage(product.getProductBigImage());
		productDTO.setProductDescription(product.getProductDescription());
		productDTO.setProductId(product.getProductId());
		productDTO.setProductRatings(product.getProductRatings());
		productDTO.setProductShortDecription(product.getProductShortDecription());
		productDTO.setProductThumbNail(product.getProductThumbNail());
		productDTO.setProductName(product.getProductName());

		Result result = productService.saveProduct(productDTO);
		createAndSendProductDetails(product);
		return result;
	}

	private ResponseEntity<String> callmeforCreateProduct(@RequestBody EmailDetails emailDeatails) {
		if (restTemplate == null) {
			restTemplate = new RestTemplate();
		}
		return restTemplate.postForEntity("http://localhost:8989/api/sendMail", emailDeatails, String.class);
	}

	private ResponseEntity<String> createAndSendProductDetails(Product product) {
		EmailDetails emailDetails = new EmailDetails();
		ObjectMapper mapper = new ObjectMapper();
		try {
			emailDetails.setMsgBody(mapper.writeValueAsString(product));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		emailDetails.setRecipient("jahnabisharma1996@gmail.com");
		emailDetails.setSubject("Product Details");
		return callmeforCreateProduct(emailDetails);
	}

	@GetMapping("/name/{productName}")
	public List<Product> getByProductName(@PathVariable String productName) {
		List<Product> listOfProduct = productService.getByProductName(productName);
		createAndSendListOfProductDetails(listOfProduct);
		return listOfProduct;
	}

	private ResponseEntity<String> createAndSendListOfProductDetails(List<Product> listOfProduct) {
		EmailDetails emailDetails = new EmailDetails();
		ObjectMapper mapper = new ObjectMapper();
		try {
			emailDetails.setMsgBody(mapper.writeValueAsString(listOfProduct));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		emailDetails.setRecipient("jahnabisharma1996@gmail.com");
		emailDetails.setSubject("Email Details");
		return callmeforCreateProduct(emailDetails);
	}

	@GetMapping("/{id}")
	public Optional<Product> getUser(@PathVariable String id) {
		Optional<Product> product = productService.getById(id);
		if (product.isPresent()) {
			createAndSendProductDetails(product.get());
		}
		return product;
	}

}
