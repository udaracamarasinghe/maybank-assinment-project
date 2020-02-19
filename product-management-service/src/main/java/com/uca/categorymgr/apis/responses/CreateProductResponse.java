package com.uca.categorymgr.apis.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.uca.categorymgr.utili.apis.ErrorDetails;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = false, value = { "status", "productId", "errorDetails" })
public class CreateProductResponse {

	private CreateProductStatus status;

	private String productId;

	private ErrorDetails errorDetails;

	public CreateProductStatus getStatus() {
		return status;
	}

	public void setStatus(CreateProductStatus httpStatus) {
		this.status = httpStatus;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public ErrorDetails getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(ErrorDetails errorDetails) {
		this.errorDetails = errorDetails;
	}

	public enum CreateProductStatus {
		CREATED, NOT_MODIFIED, INTERNAL_SERVER_ERROR, BAD_REQUEST;
	}

}
