import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/Login/login.component';
import { RegisterComponent } from './pages/Register/register.component';


import { ChatboxComponent } from './components/Chatbox/chatbox.component';


import { AuthService } from './services/auth.service'; // Assurez-vous que le AuthService est importé
import { UnauthGuard } from './guards/unauth.guard'; // Assurez-vous que le UnauthGuard est importé
import { AuthGuard } from './guards/auth.guard'; // Assurez-vous que le AuthGuard est importé

const routes: Routes = [
  { path: '', component: LoginComponent, canActivate: [UnauthGuard] },
  { path: 'register', component: RegisterComponent, canActivate: [UnauthGuard] },

  { path: 'chatbox', component: ChatboxComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
