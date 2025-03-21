package com.mdd.API.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String motdepasse; // Renommez ici si besoin pour la cohérence

    // N'est pas nécessaire à moins que vous ayez une raison d'en redéfinir
    // Mais si nécessaire : 
    public String getMotdepasse() {
        return motdepasse;
    }
    
    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
