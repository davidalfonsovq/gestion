package com.gestion.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponse<T> {
	
	private String message;
	private String status;
	
    @JsonProperty("data")
    private T data;
    
    

    public ApiResponse(String status, String message, T data) {
        this.status  = status;
        this.message = message;
        this.data = data;
    }


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
