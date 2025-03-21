package com.mdd.API.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequest {
    @JsonProperty("username")
    private String nom;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String motdepasse;

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motdepasse;
    }

    public void setMotDePasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
}
