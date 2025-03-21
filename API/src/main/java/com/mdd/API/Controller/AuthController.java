package com.mdd.API.Controller;

import com.mdd.API.DTO.AuthFailure;
import com.mdd.API.DTO.AuthSuccess;
import com.mdd.API.DTO.LoginRequest;
import com.mdd.API.DTO.RegisterRequest;

import com.mdd.API.Service.AuthService;
import com.mdd.API.model.Utilisateur;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication and registration")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
   

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String jwt = authService.authenticate(loginRequest);

        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthFailure("Invalid username or password"));
        }

        AuthSuccess authSuccess = new AuthSuccess(jwt);
        return ResponseEntity.ok(authSuccess);
    }

   
    @GetMapping("/me")
    public ResponseEntity<Utilisateur> getCurrentUser(Principal principal) {
        // Récupérer l'utilisateur par email
        Utilisateur user = authService.findUserByEmail(principal.getName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user);
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            AuthSuccess response = authService.register(registerRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
