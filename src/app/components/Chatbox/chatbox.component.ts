import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { utilisateur } from 'src/app/interfaces/user.interface';
import { Message } from 'src/app/interfaces/Message';
import { MessageService } from 'src/app/services/MessageService';

@Component({
  selector: 'app-chatbox',
  templateUrl: './chatbox.component.html',
  styleUrls: ['./chatbox.component.scss']
})
export class ChatboxComponent implements OnInit {

  messages: Message[] = [];
  newMessage: string = '';
  currentUser: utilisateur | undefined;

  constructor(
    private router: Router,
    private authService: AuthService,
    private messageService: MessageService
  ) {}

  logout(): void { 
    localStorage.removeItem('token'); 
    this.router.navigate(['/login']);
  }

  ngOnInit(): void {
    this.loadCurrentUser();
    this.loadMessages(); // Appel pour charger les messages
  }

  loadCurrentUser(): void {
    this.authService.me().subscribe(
      (data: utilisateur) => {
        this.currentUser = data;
      },
      (error: any) => {
        console.error('Erreur lors de la récupération des informations utilisateur', error);
      }
    );
  }
  loadMessages(): void {
    this.messageService.fetchMessages().subscribe(
      (data: Message[]) => {
        this.messages = data; // Chargement de la liste des messages
        console.log(this.messages); // Vérifiez ici si les messages sont chargés
      },
      (error: any) => {
        console.error('Erreur lors de la récupération des messages', error);
      }
    );
  }
  
  addMessage(): void {
    if (this.newMessage && this.currentUser) {
      const newMessage: Message = {
        contenu: this.newMessage,
        date_envoi: new Date(), // Optionnel, si géré côté backend
        id_utilisateur: this.currentUser.idUtilisateur, // Utilisateur courant
    
      };
  
      this.messageService.sendMessage(newMessage).subscribe({
        next: (response: Message) => {
          this.messages.push(response);
          this.newMessage = ''; // Réinitialisation du champ texte
        },
        error: (error: any) => {
          console.error('Erreur lors de l\'envoi du message', error);
        }
      });
    }

  }
}
