package com.uca.categorymgr.utili.services;

import com.uca.categorymgr.utili.apis.ErrorDetails;

public abstract class ServiceResponse {

	private ResponseStatus responseStatus;

	private ErrorDetails errorDetails;

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public ErrorDetails getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(ErrorDetails errorDetails) {
		this.errorDetails = errorDetails;
	}

	public enum ResponseStatus {

		SUCCESS, FAILED
	}
}
