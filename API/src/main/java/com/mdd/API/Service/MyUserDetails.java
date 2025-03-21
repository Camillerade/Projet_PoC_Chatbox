package com.mdd.API.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.mdd.API.model.Utilisateur;

import java.io.Serializable;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class MyUserDetails implements UserDetails, Serializable {

    private final Utilisateur user;
    private static final long serialVersionUID = 1L;

    public MyUserDetails (Utilisateur user) {
        this.user = user;
    }

    public Utilisateur getUser() {
        return user;
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getMotDePasse();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // Ici, vous devez ajouter les autorisations pour l'utilisateur
        // Par exemple, si vous avez un rôle "ADMIN" pour les administrateurs
        if (user.getRoles().equals("ADMIN")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return user.getIdUtilisateur(); // Assurez-vous que l'objet Utilisateur a la méthode getIdUtilisateur()
    }
}
