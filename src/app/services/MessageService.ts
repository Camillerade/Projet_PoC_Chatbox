import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Message } from '../interfaces/Message';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private apiUrl = 'http://localhost:8080/api/messages'; // URL de base

  constructor(private http: HttpClient) {}

  // Méthode pour envoyer un message
  sendMessage(message: Message): Observable<Message> {
    const token = localStorage.getItem('token'); // Récupérer le token depuis localStorage
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`, // Définir le token d'autorisation
      'Content-Type': 'application/json' // Spécifier le type de contenu
    });

    return this.http.post<Message>(`${this.apiUrl}/send`, message, { headers }); // Pointé vers /send
  }
  fetchMessages(): Observable<Message[]> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  
    return this.http.get<Message[]>(`${this.apiUrl}/show`, { headers })
      .pipe(
        catchError(err => {
          console.error('Error fetching messages', err);
          return throwError(err); // Renvoyer l'erreur pour management ultérieur
        })
      );
  }
}
