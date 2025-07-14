package com.gestion.api.model;

import java.time.LocalDateTime;

public class UsuarioResponse {
	
	private Long id;
	private LocalDateTime created;
	private LocalDateTime modified;
	private LocalDateTime lastlogin;
	private String token;
	private boolean isactived;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public LocalDateTime getModified() {
		return modified;
	}
	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}
	public LocalDateTime getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(LocalDateTime lastlogin) {
		this.lastlogin = lastlogin;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean getIsIsactived() {
		return isactived;
	}
	public void setIsactived(boolean isactived) {
		this.isactived = isactived;
	}

}
