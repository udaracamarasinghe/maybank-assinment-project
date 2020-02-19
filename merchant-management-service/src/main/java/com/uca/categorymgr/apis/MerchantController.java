package com.uca.categorymgr.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.categorymgr.dtos.MerchantDto;
import com.uca.categorymgr.services.MerchantService;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@RestController
@RequestMapping("/merchant")
public class MerchantController {

	@Autowired
	private MerchantService merchantService;

	@Cacheable("isavilable")
	@GetMapping("/isavilable/{merid}")
	public ResponseEntity<?> isavilable(@PathVariable("merid") String merid) {
		Long merId = Long.valueOf(merid);
		
		Boolean avilable_ = merchantService.isavilable(merId);

		return ResponseEntity.ok(new Object() {
			public Boolean avilable = avilable_;
		});
	}

	@Cacheable("getById")
	@GetMapping("/{merid}")
	public ResponseEntity<?> getById(@PathVariable("merid") String merid) {

		Long merId = Long.valueOf(merid);
		MerchantDto categoryDto = merchantService.findMerchantById(merId);

		return ResponseEntity.ok(categoryDto);
	}

}
