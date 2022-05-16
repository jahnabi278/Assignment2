package com.example.demo;

import java.util.ArrayList;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/ecom")
public class ProductController {
	@Autowired
	ProductService productService;
	RestTemplate restTemplate;

	@PostMapping
	public Result createProduct(@RequestBody Product product) throws Exception {
		Result result = productService.saveProduct(product);
		createAndSendProductDetails(product);
		return result;
	}

	private ResponseEntity<String> callmeforCreateProduct(@RequestBody EmailDetails emailDeatails) {
		if (restTemplate == null) {
			restTemplate = new RestTemplate();
		}
		ResponseEntity<String> sys = restTemplate.postForEntity("http://ECOM-PRODUCT-SERVICE:8989/api/sendMail",
				emailDeatails, String.class);
		return sys;
	}
	private ResponseEntity<String> createAndSendProductDetails(Product product) throws Exception {
		EmailDetails emailDetails = new EmailDetails();
		ObjectMapper mapper = new ObjectMapper();
		emailDetails.setMsgBody(mapper.writeValueAsString(product));
		emailDetails.setRecipient("jahnabisharma1996@gmail.com");
		emailDetails.setSubject("Email Details");
		ResponseEntity<String> response = callmeforCreateProduct(emailDetails);
		return response;
	}

	@GetMapping("/name/{productName}")
	public List<Product> getByProductName(@PathVariable String productName) throws Exception {
		List<Product> listOfProduct = productService.getByProductName(productName);
		createAndSendListOfProductDetails(listOfProduct);
		return listOfProduct;
	}

	private ResponseEntity<String> createAndSendListOfProductDetails(List<Product> listOfProduct) throws Exception {
		EmailDetails emailDetails = new EmailDetails();
		ObjectMapper mapper = new ObjectMapper();
		emailDetails.setMsgBody(mapper.writeValueAsString(listOfProduct));
		emailDetails.setRecipient("jahnabisharma1996@gmail.com");
		emailDetails.setSubject("Email Details");
		ResponseEntity<String> response = callmeforCreateProduct(emailDetails);
		return response;
	}

	@GetMapping("/{id}")
	Optional<Product> getUser(@PathVariable String id) throws Exception {
		Optional<Product> product = productService.getById(id);
		createAndSendProductDetails(product.get());
		return product;
	}

}
