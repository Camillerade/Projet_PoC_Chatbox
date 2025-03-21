import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { SessionService } from '../../services/session.service'; // Importe le service
import { AuthSuccess } from 'src/app/interfaces/AuthSuccess';
import { LoginRequest } from 'src/app/interfaces/LoginRequest';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  public hide = true;
  public onError = false;

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    motdepasse: ['', [Validators.required, Validators.minLength(3)]] // Changement ici
});


  constructor(
    private authService: AuthService, 
    private fb: FormBuilder, 
    private router: Router,
    private sessionService: SessionService // Injecte le service
  ) { }

  public submit(): void {
    if (this.form.valid) { 
        const loginRequest: LoginRequest = {
            email: this.form.get('email')!.value ?? '',  // Attribuer une chaîne vide par défaut
            motdepasse: this.form.get('motdepasse')!.value ?? ''  // Attribuer une chaîne vide par défaut
        };

        this.authService.login(loginRequest).subscribe(
            (response: AuthSuccess) => {
                localStorage.setItem('token', response.token);
                this.sessionService.logIn(response.user); 
                this.router.navigate(['/chatbox']); 
            },
            
            error => this.onError = true
        );
    } else {
        this.onError = true; 
    }
}



}
