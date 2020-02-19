package com.uca.categorymgr.utili.services;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
public class DeleteProductServiceResponse {

	public enum Status {
		SUCCESS, NOT_EXISTS
	}

	private Status status;

	private String message;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
