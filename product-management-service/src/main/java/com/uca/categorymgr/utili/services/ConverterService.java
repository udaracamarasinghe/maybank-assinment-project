package com.uca.categorymgr.utili.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.categorymgr.dtos.ProductDto;
import com.uca.categorymgr.entities.Product;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@Service
public class ConverterService {

	@Autowired
	private ModelMapper modelMapper;

	public ProductDto convert(Product product) {
		ProductDto map = modelMapper.map(product, ProductDto.class);

		return map;
	}

}
