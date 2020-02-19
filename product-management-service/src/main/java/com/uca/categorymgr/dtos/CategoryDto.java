package com.uca.categorymgr.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
public class CategoryDto {

	private Long catId;

	private String name;

	private CategoryDto parentCatId;

	public Long getCatId() {
		return catId;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryDto getParentCatId() {
		return parentCatId;
	}

	public void setParentCatId(CategoryDto parentCatId) {
		this.parentCatId = parentCatId;
	}

}
