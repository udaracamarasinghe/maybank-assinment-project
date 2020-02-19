package com.uca.categorymgr.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.uca.categorymgr.dtos.CategoryDto;
import com.uca.categorymgr.dtos.MerchantDto;
import com.uca.categorymgr.dtos.NewProductDto;
import com.uca.categorymgr.dtos.ProductDto;
import com.uca.categorymgr.dtos.UpdateProductDto;
import com.uca.categorymgr.entities.Product;
import com.uca.categorymgr.entities.QProduct;
import com.uca.categorymgr.repositories.ProductRepository;
import com.uca.categorymgr.services.integrations.CategoryServiceIntegrationService;
import com.uca.categorymgr.services.integrations.IntegrationFailException;
import com.uca.categorymgr.services.integrations.MerchantServiceIntegrationService;
import com.uca.categorymgr.utili.apis.ErrorDetails;
import com.uca.categorymgr.utili.services.ConverterService;
import com.uca.categorymgr.utili.services.CreateProductServiceResponse;
import com.uca.categorymgr.utili.services.DeleteProductServiceResponse;
import com.uca.categorymgr.utili.services.DeleteProductServiceResponse.Status;
import com.uca.categorymgr.utili.services.HashingService;
import com.uca.categorymgr.utili.services.ServiceResponse.ResponseStatus;
import com.uca.categorymgr.utili.services.UpdateProductServiceResponse;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@Service
public class ProductService {

	private static final Logger logger = LogManager.getLogger(CategoryServiceIntegrationService.class.getSimpleName());

	@Autowired
	private CategoryServiceIntegrationService categoryServiceIntegrationService;

	@Autowired
	private MerchantServiceIntegrationService merchantServiceIntegrationService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ConverterService converterService;

	@Autowired
	private HashingService hashingService;

	@Transactional(propagation = Propagation.REQUIRED)
	public CreateProductServiceResponse createProduct(NewProductDto newProductDto) {
		CreateProductServiceResponse createProductServiceResponse = new CreateProductServiceResponse();
		ErrorDetails errorDetails = null;

		try {
			if (categoryServiceIntegrationService.isCategoryAvilable(newProductDto.getCategoryId())
					&& merchantServiceIntegrationService.isMerchantAvilable(newProductDto.getMerchantId())) {
				Product product = new Product();

				product.setTitle(newProductDto.getTitle());
				product.setDescription(newProductDto.getDescription());
				product.setIsAvilable(newProductDto.getIsAvilable());
				product.setProductPageUrl(newProductDto.getProductPageUrl());
				product.setImageURL(newProductDto.getImageURL());

				product.setPrice(newProductDto.getPrice());
				product.setMsrp(newProductDto.getMsrp());

				product.setMerchantId(newProductDto.getMerchantId());
				product.setCategoryId(newProductDto.getCategoryId());

				Product createdProduct = productRepository.save(product);

				String hashedProductId = hashingService.hash(createdProduct.getProductId());

				createProductServiceResponse.setProductId(hashedProductId);
				createProductServiceResponse.setResponseStatus(ResponseStatus.SUCCESS);
			} else {
				createProductServiceResponse.setResponseStatus(ResponseStatus.FAILED);

				errorDetails = new ErrorDetails(new Date(), "Invalid category id or merchant.",
						"Invalid category id or merchant.");
				createProductServiceResponse.setErrorDetails(errorDetails);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);

			errorDetails = new ErrorDetails(new Date(), ex.getMessage(), ex.getMessage());

			createProductServiceResponse.setResponseStatus(ResponseStatus.FAILED);
			createProductServiceResponse.setErrorDetails(errorDetails);
		}

		return createProductServiceResponse;
	}

	public List<ProductDto> searchBy(String byName, Long byCategory) {
		BooleanBuilder where = new BooleanBuilder();

		if (byName != null) {
			where.and(QProduct.product.title.likeIgnoreCase("%" + byName + "%"));
		}
		if (byCategory != null) {
			where.and(QProduct.product.categoryId.eq(byCategory));
		}

		List<ProductDto> dtos = new ArrayList<>();

		Iterable<Product> employees2 = productRepository.findAll(where);
		employees2.forEach(product -> {
			Future<CategoryDto> categoryByIdAsync = null;
			try {
				categoryByIdAsync = categoryServiceIntegrationService.findCategoryByIdAsync(product.getCategoryId());
			} catch (IntegrationFailException e) {
				logger.error(e.getMessage(), e);
			}

			Future<MerchantDto> merchantByIdAsync = null;
			try {
				merchantByIdAsync = merchantServiceIntegrationService.findMerchantByIdAsync(product.getMerchantId());
			} catch (IntegrationFailException e) {
				logger.error(e.getMessage(), e);
			}

			ProductDto convert = converterService.convert(product);

			while ((categoryByIdAsync != null && !categoryByIdAsync.isDone())
					|| (merchantByIdAsync != null && !merchantByIdAsync.isDone())) {
			}

			if (categoryByIdAsync != null)
				try {
					convert.setCategory(categoryByIdAsync.get());
				} catch (InterruptedException | ExecutionException e) {
					logger.error(e.getMessage(), e);
				}

			if (merchantByIdAsync != null)
				try {
					convert.setMerchant(merchantByIdAsync.get());
				} catch (InterruptedException | ExecutionException e) {
					logger.error(e.getMessage(), e);
				}

			convert.setProId(hashingService.hash(product.getProductId()));

			dtos.add(convert);
		});

		return dtos;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public UpdateProductServiceResponse updateProduct(UpdateProductDto newProductDto) {
		UpdateProductServiceResponse updateProductServiceResponse = new UpdateProductServiceResponse();
		ErrorDetails errorDetails = null;

		Long productId = hashingService.decode(newProductDto.getProductId());

		if (!productRepository.existsById(productId)) {
			errorDetails = new ErrorDetails(new Date(), "Product id not exists.", "Product id not exists");
			updateProductServiceResponse.setResponseStatus(ResponseStatus.FAILED);
			updateProductServiceResponse.setErrorDetails(errorDetails);

			return updateProductServiceResponse;
		}

		try {
			if (categoryServiceIntegrationService.isCategoryAvilable(newProductDto.getCategoryId())
					&& merchantServiceIntegrationService.isMerchantAvilable(newProductDto.getMerchantId())) {
				Product product = productRepository.findById(productId).get();

				product.setTitle(newProductDto.getTitle());
				product.setDescription(newProductDto.getDescription());
				product.setIsAvilable(newProductDto.getIsAvilable());
				product.setProductPageUrl(newProductDto.getProductPageUrl());
				product.setImageURL(newProductDto.getImageURL());

				product.setPrice(newProductDto.getPrice());
				product.setMsrp(newProductDto.getMsrp());

				product.setMerchantId(newProductDto.getMerchantId());
				product.setCategoryId(newProductDto.getCategoryId());

				productRepository.save(product);

				updateProductServiceResponse.setResponseStatus(ResponseStatus.SUCCESS);
			} else {
				updateProductServiceResponse.setResponseStatus(ResponseStatus.FAILED);

				errorDetails = new ErrorDetails(new Date(), "Invalid category id or merchant.",
						"Invalid category id or merchant.");
				updateProductServiceResponse.setErrorDetails(errorDetails);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);

			errorDetails = new ErrorDetails(new Date(), ex.getMessage(), ex.getMessage());

			updateProductServiceResponse.setResponseStatus(ResponseStatus.FAILED);
			updateProductServiceResponse.setErrorDetails(errorDetails);
		}

		return updateProductServiceResponse;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public DeleteProductServiceResponse deleteProduct(String productid) {
		DeleteProductServiceResponse deleteProductServiceResponse = new DeleteProductServiceResponse();

		Long productId = hashingService.decode(productid);

		if (!productRepository.existsById(productId)) {
			deleteProductServiceResponse.setStatus(Status.NOT_EXISTS);

			return deleteProductServiceResponse;
		}

		productRepository.deleteById(productId);

		deleteProductServiceResponse.setStatus(Status.SUCCESS);

		return deleteProductServiceResponse;
	}

}
