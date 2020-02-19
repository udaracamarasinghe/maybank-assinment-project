package com.uca.categorymgr.dtos;

import java.math.BigDecimal;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
public class ProductDto {

	private String proId;

	private String title;

	private BigDecimal price;

	private BigDecimal msrp;

	private Boolean isAvilable;

	private String description;

	private String imageURL;

	private String productPageUrl;

	private CategoryDto category;

	private MerchantDto merchant;

	public String getProId() {
		return proId;
	}

	public void setProId(String id) {
		this.proId = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getMsrp() {
		return msrp;
	}

	public void setMsrp(BigDecimal msrp) {
		this.msrp = msrp;
	}

	public Boolean getIsAvilable() {
		return isAvilable;
	}

	public void setIsAvilable(Boolean isAvilable) {
		this.isAvilable = isAvilable;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
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

	public MerchantDto getMerchant() {
		return merchant;
	}

	public void setMerchant(MerchantDto merchant) {
		this.merchant = merchant;
	}

}
