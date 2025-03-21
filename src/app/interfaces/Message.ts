// src/app/interfaces/message.interface.ts
export interface Message {
  id_message?: number; // Identifiant unique du message (optionnel car généré par le backend)
  contenu: string; // Contenu du message (requis)
  date_envoi?: Date; // Date d'envoi du message (optionnel)
  id_utilisateur?: number; // Identifiant de l'utilisateur qui envoie le message (optionnel)
  id_utilisateur_destinataire?: number; // Identifiant de l'utilisateur destinataire du message (optionnel)
}
