package com.uca.categorymgr.services.integrations;

import java.util.LinkedHashMap;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uca.categorymgr.dtos.CategoryDto;

@Service
public class CategoryServiceIntegrationService {

	private static final Logger logger = LogManager.getLogger(CategoryServiceIntegrationService.class.getSimpleName());

	@Value("${categoryservice.host}")
	private String categoryServiceHost;

	@Autowired
	private RestTemplate restTemplate;

	HttpHeaders commonHeaders = null;

	public CategoryServiceIntegrationService() {
		commonHeaders = new HttpHeaders();
		commonHeaders.setContentType(MediaType.APPLICATION_JSON);
	}

	public Boolean isCategoryAvilable(Long catId) throws IntegrationFailException {

		HttpEntity<?> entity = new HttpEntity<>(commonHeaders);

		StringBuilder url = new StringBuilder();
		url.append(categoryServiceHost).append("/category/isavilable/").append(catId);

		ResponseEntity<LinkedHashMap> response = null;

		try {
			response = restTemplate.exchange(url.toString(), HttpMethod.GET, entity, LinkedHashMap.class);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);

			throw new IntegrationFailException(ex.getMessage(), ex);
		}

		if (response == null || response.getStatusCode() != HttpStatus.OK) {
			throw new IntegrationFailException("Invalid response for isCategoryAvilable.");
		} else {
			LinkedHashMap<?, ?> body = response.getBody();

			Boolean returnValue = body.containsKey("avilable") && (Boolean) body.get("avilable");

			return returnValue;
		}
	}

	public CategoryDto findCategoryById(Long catId) throws IntegrationFailException {
		HttpEntity<?> entity = new HttpEntity<>(commonHeaders);

		StringBuilder url = new StringBuilder();
		url.append(categoryServiceHost).append("/category/").append(catId);

		ResponseEntity<CategoryDto> response = null;

		try {
			response = restTemplate.exchange(url.toString(), HttpMethod.GET, entity, CategoryDto.class);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);

			throw new IntegrationFailException(ex.getMessage(), ex);
		}

		if (response == null || response.getStatusCode() != HttpStatus.OK) {
			throw new IntegrationFailException("Invalid response for findCategoryById.");
		} else {
			CategoryDto body = response.getBody();

			return body;
		}
	}

	@Async
	public Future<CategoryDto> findCategoryByIdAsync(Long catId) throws IntegrationFailException {
		return new AsyncResult<CategoryDto>(findCategoryById(catId));
	}
}
