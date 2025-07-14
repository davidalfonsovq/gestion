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


	// Getters y setters
	
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param the status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param the data
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Gets the message.
	 *
	 * @param the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param the message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
