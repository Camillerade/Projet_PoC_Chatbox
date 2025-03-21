import { utilisateur } from "./user.interface";

export interface AuthSuccess {
    token: string;
    user: utilisateur; // Assure-toi que le champ user est de type User
  }
  