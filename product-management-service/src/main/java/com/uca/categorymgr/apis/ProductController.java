package com.uca.categorymgr.apis;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uca.categorymgr.apis.responses.CreateProductResponse;
import com.uca.categorymgr.apis.responses.CreateProductResponse.CreateProductStatus;
import com.uca.categorymgr.apis.responses.DeleteProductResponse;
import com.uca.categorymgr.apis.responses.UpdateProductResponse;
import com.uca.categorymgr.apis.responses.UpdateProductResponse.UpdateProductStatus;
import com.uca.categorymgr.dtos.NewProductDto;
import com.uca.categorymgr.dtos.ProductDto;
import com.uca.categorymgr.dtos.UpdateProductDto;
import com.uca.categorymgr.services.ProductService;
import com.uca.categorymgr.utili.services.CreateProductServiceResponse;
import com.uca.categorymgr.utili.services.DeleteProductServiceResponse;
import com.uca.categorymgr.utili.services.UpdateProductServiceResponse;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<?> get() {
		return ResponseEntity.ok("OK");
	}

	@PostMapping
	public ResponseEntity<?> createProduct(@Valid @RequestBody NewProductDto newProductDto) {
		CreateProductServiceResponse createProductServiceResponse = productService.createProduct(newProductDto);

		CreateProductResponse createProductResponse = new CreateProductResponse();
		if (createProductServiceResponse.getResponseStatus() == CreateProductServiceResponse.ResponseStatus.SUCCESS) {
			createProductResponse.setProductId(createProductServiceResponse.getProductId());
			createProductResponse.setStatus(CreateProductStatus.CREATED);
		} else {
			createProductResponse.setStatus(CreateProductStatus.NOT_MODIFIED);
			createProductResponse.setErrorDetails(createProductServiceResponse.getErrorDetails());
		}

		return ResponseEntity.ok(createProductResponse);
	}

	@GetMapping("search")
	public ResponseEntity<?> searchBy(@RequestParam(value = "name", required = false) String byName,
			@RequestParam(value = "category", required = false) Long byCategory) {
		List<ProductDto> list = productService.searchBy(byName, byCategory);

		return ResponseEntity.ok(list);
	}

	@PutMapping
	public ResponseEntity<?> updateProduct(@Valid @RequestBody UpdateProductDto newProductDto) {
		UpdateProductServiceResponse updateProductServiceResponse = productService.updateProduct(newProductDto);

		UpdateProductResponse createProductResponse = new UpdateProductResponse();
		if (updateProductServiceResponse.getResponseStatus() == CreateProductServiceResponse.ResponseStatus.SUCCESS) {
			createProductResponse.setStatus(UpdateProductStatus.UPDATED);
		} else {
			createProductResponse.setStatus(UpdateProductStatus.NOT_MODIFIED);
			createProductResponse.setErrorDetails(updateProductServiceResponse.getErrorDetails());
		}

		return ResponseEntity.ok(createProductResponse);
	}

	@DeleteMapping("/{proid}")
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "proid", required = true) String productId) {
		DeleteProductServiceResponse updateProductServiceResponse = productService.deleteProduct(productId);

		DeleteProductResponse createProductResponse = new DeleteProductResponse();
		if (updateProductServiceResponse.getStatus() == DeleteProductServiceResponse.Status.SUCCESS) {
			createProductResponse.setStatus(DeleteProductResponse.UpdateProductStatus.DELETED);
		} else if (updateProductServiceResponse.getStatus() == DeleteProductServiceResponse.Status.NOT_EXISTS) {
			createProductResponse.setStatus(DeleteProductResponse.UpdateProductStatus.BAD_REQUEST);
		} else {
			createProductResponse.setStatus(DeleteProductResponse.UpdateProductStatus.NOT_MODIFIED);
		}

		return ResponseEntity.ok(createProductResponse);
	}

}
