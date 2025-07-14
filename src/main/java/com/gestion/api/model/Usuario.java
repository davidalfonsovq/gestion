package com.gestion.api.model;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

/**
 * The Class Usuario.
 */
@Entity
@Table(name = "USUARIO")
public class Usuario {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long id;

	/** The name. */
	@Column (name = "name")
	private String name;

	/** The email. */
	@Column (name = "email")
	@Email(message = "El correo no tiene un formato válido (ej: nombre@dominio.com)")
	private String email;
	
	/** The password. */
	@Column (name = "password")
	private String password;
	
	/** The lastlogin. */
	@Column (name = "lastlogin")
	@CreationTimestamp
	private LocalDateTime lastlogin;
	
	/** The created. */
	@Column (name = "created")
	@CreationTimestamp
	private LocalDateTime created;
	
	/** The modified. */
	@Column (name = "modified")
	@CreationTimestamp
	private LocalDateTime modified;
	
	/** The token. */
	@UuidGenerator
	@Column (name = "token")
	private UUID token;
	
	/** The isactived. */
	@Column (name = "isactived")
	private boolean isactived;
	
	
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Telefono> phones;

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
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @param lastlogin the new lastlogin
	 */
    public void setLastlogin(LocalDateTime lastlogin) {
        this.lastlogin = lastlogin;
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
	 * @param created the new created
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
	 * @param modified the new modified
	 */
    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
	
	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public UUID getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 *
	 * @return the token
	 */
	public void setToken(UUID token) {
		this.token = token;
	}

	/**
	 * Gets the isactived.
	 *
	 * @return the isactived
	 */
	public boolean getIsActived() {
		return isactived;
	}

	/**
	 * Sets the isactived.
	 *
	 * @return the isactived
	 */
	public void setIsActived(boolean isactived) {
		this.isactived = isactived;
	}
	
	public List<Telefono> getPhones() { return phones; }
    public void setPhones(List<Telefono> phones) {
        this.phones = phones;
        if (phones != null) {
            phones.forEach(p -> p.setUsuario(this));
        }
    }


}