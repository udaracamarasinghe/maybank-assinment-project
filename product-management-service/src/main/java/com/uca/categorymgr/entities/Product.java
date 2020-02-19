package com.uca.categorymgr.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;

	private String title;

	@Column(precision = 10, scale = 2)
	private Double price;

	@Column(precision = 10, scale = 2)
	private Double msrp;

	private Boolean isAvilable;

	private Long categoryId;

	private String imageURL;

	private String productPageUrl;

	private String description;

	private Long merchantId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getMsrp() {
		return msrp;
	}

	public void setMsrp(Double msrp) {
		this.msrp = msrp;
	}

	public Boolean getIsAvilable() {
		return isAvilable;
	}

	public void setIsAvilable(Boolean isAvilable) {
		this.isAvilable = isAvilable;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getProductPageUrl() {
		return productPageUrl;
	}

	public void setProductPageUrl(String productPageUrl) {
		this.productPageUrl = productPageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

}
