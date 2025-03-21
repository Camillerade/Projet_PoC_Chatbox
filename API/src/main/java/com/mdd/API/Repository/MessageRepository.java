package com.mdd.API.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mdd.API.model.Message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByIdUtilisateur(Long idUtilisateur); // Pour récupérer les messages envoyés par l'utilisateur
    List<Message> findByIdUtilisateurDestinataire(Long idUtilisateurDestinataire); // Pour récupérer les messages reçus par l'utilisateur
}
