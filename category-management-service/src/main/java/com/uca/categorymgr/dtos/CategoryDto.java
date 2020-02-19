package com.uca.categorymgr.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
public class CategoryDto {

	@NotNull
	@NotEmpty
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
