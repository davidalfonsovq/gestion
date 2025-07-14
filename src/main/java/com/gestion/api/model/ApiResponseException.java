package com.gestion.api.model;


public class ApiResponseException {
	
	private String message;
	private String status;
	
    public ApiResponseException(String status, String message) {
        this.status  = status;
        this.message = message;
    }


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
