package com.uca.categorymgr.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long catId;

	private String name;

	private Long parentCatId;

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

	public Long getParentCatId() {
		return parentCatId;
	}

	public void setParentCatId(Long parentCatId) {
		this.parentCatId = parentCatId;
	}

}
