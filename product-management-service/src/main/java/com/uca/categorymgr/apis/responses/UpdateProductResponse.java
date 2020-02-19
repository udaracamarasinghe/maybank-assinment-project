package com.uca.categorymgr.apis.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.uca.categorymgr.utili.apis.ErrorDetails;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = false, value = { "status", "errorDetails" })
public class UpdateProductResponse {

	private UpdateProductStatus status;

	private ErrorDetails errorDetails;

	public UpdateProductStatus getStatus() {
		return status;
	}

	public void setStatus(UpdateProductStatus httpStatus) {
		this.status = httpStatus;
	}

	public ErrorDetails getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(ErrorDetails errorDetails) {
		this.errorDetails = errorDetails;
	}

	public enum UpdateProductStatus {
		UPDATED, NOT_MODIFIED, INTERNAL_SERVER_ERROR, BAD_REQUEST;
	}

}
