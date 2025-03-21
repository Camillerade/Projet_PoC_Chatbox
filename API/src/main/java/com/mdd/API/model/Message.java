package com.mdd.API.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_message")
    private Long idMessage;

    @JsonProperty("contenu")
    @Column(name = "contenu")
    private String contenu;

    @JsonProperty("date_envoi")
    @Column(name = "date_envoi")
    private LocalDateTime dateEnvoi;

    @JsonProperty("id_utilisateur")
    @Column(name = "id_utilisateur")
    private Long idUtilisateur;

    @Column(name = "id_utilisateur_destinataire")
    private Long idUtilisateurDestinataire;

    // Getters et Setters
    public Long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Long idMessage) {
        this.idMessage = idMessage;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDateTime getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Long getIdUtilisateurDestinataire() {
        return idUtilisateurDestinataire;
    }

    public void setIdUtilisateurDestinataire(Long idUtilisateurDestinataire) {
        this.idUtilisateurDestinataire = idUtilisateurDestinataire;
    }
}
