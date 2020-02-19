package com.uca.categorymgr.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
public class UpdateProductDto {

	@NotNull
	@NotEmpty
	private String productId;

	@NotNull
	@NotEmpty
	private String title;

	@NotNull
	private Double price;

	@NotNull
	private Double msrp;

	@NotNull
	private Boolean isAvilable;

	@NotNull
	private Long categoryId;

	private String imageURL;

	private String productPageUrl;

	@NotNull
	@NotEmpty
	private String description;

	@NotNull
	private Long merchantId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
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
