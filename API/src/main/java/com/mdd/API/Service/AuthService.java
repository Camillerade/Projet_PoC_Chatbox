package com.mdd.API.Service;

import com.mdd.API.DTO.AuthSuccess;
import com.mdd.API.DTO.LoginRequest;
import com.mdd.API.DTO.RegisterRequest;
import com.mdd.API.model.Utilisateur;
import com.mdd.API.Repository.UserRepository;
import com.mdd.API.Configuration.JwtProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtProvider jwtProvider, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticate(LoginRequest loginRequest) {
        try {
            Utilisateur user = userRepository.findByEmail(loginRequest.getEmail());
            if (user == null) {
                throw new BadCredentialsException("User not found");
            }

            if (!passwordEncoder.matches(loginRequest.getMotdepasse(), user.getMotDePasse())) {
                throw new BadCredentialsException("Incorrect password");
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getMotdepasse()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtProvider.generateJwtToken(userDetails.getUsername());
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    public AuthSuccess register(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use");
        }

        Utilisateur newUser = new Utilisateur();
        newUser.setNom(registerRequest.getNom());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setMotDePasse(passwordEncoder.encode(registerRequest.getMotDePasse())); // Hachage du mot de passe
        
        userRepository.save(newUser); // Enregistrement de l'utilisateur

        return new AuthSuccess("Registration successful");
    }

    public Utilisateur findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
