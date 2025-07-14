package com.gestion.api.model;

import java.time.LocalDateTime;

public class UsuarioResponse {
	
	private Long id;
	private LocalDateTime created;
	private LocalDateTime modified;
	private LocalDateTime lastlogin;
	private String token;
	private boolean isactived;
	
	
	// Getters y setters
	
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @return the id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	public LocalDateTime getCreated() {
		return created;
	}
	
	/**
	 * Sets the created.
	 *
	 * @return the created
	 */
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
	/**
	 * Gets the modified.
	 *
	 * @return the modified
	 */
	public LocalDateTime getModified() {
		return modified;
	}
	
	/**
	 * Sets the modified.
	 *
	 * @return the modified
	 */
	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}
	
	/**
	 * Gets the lastlogin.
	 *
	 * @return the lastlogin
	 */
	public LocalDateTime getLastlogin() {
		return lastlogin;
	}
	
	/**
	 * Sets the lastlogin.
	 *
	 * @return the lastlogin
	 */
	public void setLastlogin(LocalDateTime lastlogin) {
		this.lastlogin = lastlogin;
	}
	
	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	
	/**
	 * Sets the token.
	 *
	 * @return the token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
	/**
	 * Gets the isactived.
	 *
	 * @return the isactived
	 */
	public boolean getIsIsactived() {
		return isactived;
	}
	
	/**
	 * Sets the isactived.
	 *
	 * @return the isactived
	 */
	public void setIsactived(boolean isactived) {
		this.isactived = isactived;
	}

}
