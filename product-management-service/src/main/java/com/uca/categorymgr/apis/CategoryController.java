package com.uca.categorymgr.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	private CategoryService customerService;

	@GetMapping
	public ResponseEntity<?> get() {
		return ResponseEntity.ok("OK");
	}

}
