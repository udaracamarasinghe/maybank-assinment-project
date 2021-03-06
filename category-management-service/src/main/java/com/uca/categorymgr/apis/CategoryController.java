package com.uca.categorymgr.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.categorymgr.dtos.CategoryDto;
import com.uca.categorymgr.services.CategoryService;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Cacheable("isavilable")
	@GetMapping("/isavilable/{catid}")
	public ResponseEntity<?> isavilable(@PathVariable("catid") String catid) {

		Long catId = Long.valueOf(catid);
		Boolean avilable_ = categoryService.isavilable(catId);

		return ResponseEntity.ok(new Object() {
			public Boolean avilable = avilable_;
		});
	}

	@Cacheable("getById")
	@GetMapping("/{catid}")
	public ResponseEntity<?> getById(@PathVariable("catid") String catid) {

		Long catId = Long.valueOf(catid);
		CategoryDto categoryDto = categoryService.findCategoryById(catId);

		return ResponseEntity.ok(categoryDto);
	}

}
