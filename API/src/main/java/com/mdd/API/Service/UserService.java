package com.mdd.API.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.mdd.API.model.Utilisateur;
import com.mdd.API.DTO.RegisterRequest;
import com.mdd.API.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean userExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public Utilisateur register(RegisterRequest registerRequest) {
        System.out.println("Enregistrement de l'utilisateur : " + registerRequest.getEmail());

        if (userExists(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Email is already in use");
        }

        Utilisateur user = new Utilisateur();
        user.setEmail(registerRequest.getEmail());
        user.setNom(registerRequest.getNom());
        user.setMotDePasse(passwordEncoder.encode(registerRequest.getMotDePasse()));

        try {
            Utilisateur savedUser = userRepository.save(user);
            System.out.println("Utilisateur enregistré avec succès : " + savedUser.getIdUtilisateur());
            return savedUser;
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("constraint [unique_email]")) {
                throw new IllegalArgumentException("Email is already in use", e);
            }
            throw new RuntimeException("Error saving user to the database", e);
        }
    }

    public void registerUser(RegisterRequest registerRequest) {
        register(registerRequest);
    }
   

    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("No authenticated user found");
        }

        // Récupérer les détails de l'utilisateur
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        // Si UserDetails inclut l'ID de l'utilisateur, le renvoyer
        // Assurez-vous que votre UserDetails contient bien cette information
        if (userDetails instanceof Utilisateur) {
            return ((Utilisateur) userDetails).getIdUtilisateur();
        } else {
            throw new RuntimeException("User details not found");
        }
    }
}
