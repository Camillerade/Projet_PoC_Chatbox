package com.mdd.API.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mdd.API.Repository.MessageRepository;
import com.mdd.API.Repository.UserRepository;
import com.mdd.API.Service.MyUserDetails;
import com.mdd.API.model.Message;
import com.mdd.API.model.Utilisateur;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message) {
        validateMessageContent(message);
        
        Long idUtilisateurConnecte = getConnectedUserId();
        Utilisateur randomSupport = getRandomSupport();

        Message messageToSend = createMessage(message, idUtilisateurConnecte, randomSupport);

        return messageRepository.save(messageToSend);
    }

    // Endpoint pour récupérer tous les messages de l'utilisateur connecté
    @GetMapping("/show")
    public List<Message> getAllMessagesForUser() {
        Long idUtilisateurConnecte = getConnectedUserId(); // Récupérer l'ID de l'utilisateur connecté

        // Récupérer tous les messages envoyés et reçus par l'utilisateur
        List<Message> messagesEnvoyes = messageRepository.findByIdUtilisateur(idUtilisateurConnecte);
        List<Message> messagesRecus = messageRepository.findByIdUtilisateurDestinataire(idUtilisateurConnecte);

        // Combiner les deux listes de messages
        List<Message> allMessages = new ArrayList<>();
        allMessages.addAll(messagesEnvoyes);
        allMessages.addAll(messagesRecus);

        return allMessages; // Retourner tous les messages
    }

    private void validateMessageContent(Message message) {
        if (message.getContenu() == null || message.getContenu().isEmpty()) {
            throw new IllegalArgumentException("Le contenu du message ne doit pas être vide.");
        }
    }

    private Long getConnectedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            return userDetails.getId();
        } else {
            throw new IllegalArgumentException("Vous devez être connecté pour envoyer un message.");
        }
    }

    private Utilisateur findUserById(Long id) {
        return userRepository.findById(id)
                             .orElseThrow(() -> new IllegalStateException("Utilisateur non trouvé."));
    }

    private Utilisateur getRandomSupport() {
        List<Utilisateur> supports = userRepository.findByRole("support");
        if (supports.isEmpty()) {
            throw new IllegalStateException("Aucun utilisateur support disponible.");
        }
        Random random = new Random();
        return supports.get(random.nextInt(supports.size()));
    }

    private Message createMessage(Message message, Long idUtilisateurConnecte, Utilisateur randomSupport) {
        Message messageToSend = new Message();
        messageToSend.setIdUtilisateur(idUtilisateurConnecte);
        messageToSend.setIdUtilisateurDestinataire(randomSupport.getIdUtilisateur());
        messageToSend.setContenu(message.getContenu());
        messageToSend.setDateEnvoi(LocalDateTime.now());
        return messageToSend;
    }
}
