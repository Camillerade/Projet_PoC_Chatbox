package com.mdd.API.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mdd.API.Repository.MessageRepository;

import com.mdd.API.model.Message;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    

    public void envoyerMessage(Long idUtilisateur, Long idUtilisateurDestinataire, String contenu) {
        Message message = new Message();
        message.setIdUtilisateur(idUtilisateur);
        message.setIdUtilisateurDestinataire(idUtilisateurDestinataire);
        message.setContenu(contenu);
        message.setDateEnvoi(LocalDateTime.now());
        messageRepository.save(message);
    }




}
