package com.uca.categorymgr.utili.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.categorymgr.dtos.CategoryDto;
import com.uca.categorymgr.entities.Category;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@Service
public class ConverterService {

	@Autowired
	private ModelMapper modelMapper;

	public CategoryDto convert(Category post) {
		CategoryDto customerDto = modelMapper.map(post, CategoryDto.class);

		return customerDto;
	}

}
