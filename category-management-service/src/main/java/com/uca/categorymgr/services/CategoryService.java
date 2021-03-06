package com.uca.categorymgr.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uca.categorymgr.dtos.CategoryDto;
import com.uca.categorymgr.entities.Category;
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
	private CategoryRepository categoryRepository;

	@Autowired
	private ConverterService converterService;

	public Boolean isavilable(Long catid) {
		return categoryRepository.existsById(catid);
	}

	public CategoryDto findCategoryById(Long id) {
		Optional<Category> findById = categoryRepository.findById(id);

		if (!findById.isPresent()) {
			return null;
		}

		return converterService.convert(findById.get());
	}

}
