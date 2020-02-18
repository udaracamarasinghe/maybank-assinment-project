package com.uca.categorymgr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.categorymgr.repositories.CategoryRepository;
import com.uca.categorymgr.utili.services.ConverterService;
import com.uca.categorymgr.utili.services.HashingService;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@Service
public class CategoryService {

	@Autowired
	private CategoryRepository customerRepository;

	@Autowired
	private ConverterService converterService;

	@Autowired
	private HashingService hashingService;

}
