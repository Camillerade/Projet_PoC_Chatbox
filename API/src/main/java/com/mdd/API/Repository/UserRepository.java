package com.mdd.API.Repository;

import com.mdd.API.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);
 
    List<Utilisateur> findByRole(String role);
}
