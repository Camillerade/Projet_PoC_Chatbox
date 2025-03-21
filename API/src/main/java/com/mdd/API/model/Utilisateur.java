package com.mdd.API.model;

import java.sql.Date;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "motdepasse", nullable = false)
    private String motdepasse;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "dateDeNaissance")
    private Date dateDeNaissance;

    @Column(name = "role", nullable = false, columnDefinition = "varchar(255) default 'client'")
    private String role = "client";  // Valeur par défaut

    // Constructeur par défaut
    public Utilisateur() {
        // La valeur par défaut pour 'role' est déjà définie ci-dessus
    }

    // Constructeur avec paramètres sans rôle
    public Utilisateur(String nom, String prenom, String email, String motDePasse, String adresse, Date dateDeNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motdepasse = motDePasse;
        this.adresse = adresse;
        this.dateDeNaissance = dateDeNaissance;
        this.role = "client"; // Définition du rôle par défaut
    }

    // Constructeur avec paramètres incluant rôle
    public Utilisateur(String nom, String prenom, String email, String motDePasse, String adresse, Date dateDeNaissance, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motdepasse = motDePasse;
        this.adresse = adresse;
        this.dateDeNaissance = dateDeNaissance;
        this.role = role;
    }

    // Getters et Setters
    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public void setMotDePasse(String motDePasse) {
        this.motdepasse = motDePasse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getRoles() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


}
