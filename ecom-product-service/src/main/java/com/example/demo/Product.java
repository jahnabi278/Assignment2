package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
	@Id
	private String product_id;
	private String productName;
	private String product_big_Image;
	private String product_thumb_nail;
	private String product_description;
	private String product_short_decription;
	private int product_ratings;
	private double price;
	private boolean isActive;

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProduct_big_Image() {
		return product_big_Image;
	}

	public void setProduct_big_Image(String product_big_Image) {
		this.product_big_Image = product_big_Image;
	}

	public String getProduct_thumb_nail() {
		return product_thumb_nail;
	}

	public void setProduct_thumb_nail(String product_thumb_nail) {
		this.product_thumb_nail = product_thumb_nail;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public String getProduct_short_decription() {
		return product_short_decription;
	}

	public void setProduct_short_decription(String product_short_decription) {
		this.product_short_decription = product_short_decription;
	}

	public int getProduct_ratings() {
		return product_ratings;
	}

	public void setProduct_ratings(int product_ratings) {
		this.product_ratings = product_ratings;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean getisActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
