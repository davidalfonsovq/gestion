package com.gestion.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private String citycode;
    private String contrycode;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonBackReference
    private Usuario usuario;

    // Getters y setters
    public Long getId() { return id; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getCitycode() { return citycode; }
    public void setCitycode(String citycode) { this.citycode = citycode; }

    public String getContrycode() { return contrycode; }
    public void setContrycode(String contrycode) { this.contrycode = contrycode; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
  
}