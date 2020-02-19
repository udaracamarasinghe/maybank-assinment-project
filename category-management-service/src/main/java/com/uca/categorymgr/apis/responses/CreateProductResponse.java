package com.uca.categorymgr.apis.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.uca.categorymgr.utili.apis.ErrorDetails;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = false, value = { "httpStatus", "productId", "errorDetails" })
public class CreateProductResponse {

	private CreateProductHttpStatus httpStatus;

	private String productId;

	private ErrorDetails errorDetails;

	public CreateProductHttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(CreateProductHttpStatus httpStatus) {
		this.httpStatus = httpStatus;
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

	public enum CreateProductHttpStatus {
		CREATED(201), NOT_MODIFIED(304), INTERNAL_SERVER_ERROR(500), BAD_REQUEST(400);

		private final int value;

		CreateProductHttpStatus(int codeA) {
			this.value = codeA;
		}

		@JsonValue
		public int value() {
			return value;
		}

	}

}
