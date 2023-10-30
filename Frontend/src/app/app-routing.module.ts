import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SeatsComponent } from './components/seats/seats.component';
import { CustomSeatLayoutComponent } from './components/custom-seat-layout/custom-seat-layout.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { TheatresComponent } from './components/theatres/theatres.component';
import { AuthGuard } from './services/auth.guard';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { AddMovieComponent } from './components/add-movie/add-movie.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { TicketsComponent } from './components/tickets/tickets.component';
import { EditMovieComponent } from './components/edit-movie/edit-movie.component';
import { RoleGuard } from './services/role.guard';


const routes: Routes = [
  {
    path:"",
    component:HomeComponent
  },
  {
    path:'seats/:movie/:theatre',
    component:CustomSeatLayoutComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'login',
    component:LoginComponent,
    pathMatch:"full"
  },{
    path:'theatres/:movie',
    component:TheatresComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'register',
    component:RegisterUserComponent,
  },
  {
    path:'addMovie',
    component:AddMovieComponent,
    canActivate:[RoleGuard]
  },{
    path:'updateUser',
    component:UpdateUserComponent,
    canActivate:[AuthGuard]
  },{
    path:'forgotPassword',
    component:ForgotPasswordComponent
  },{
    path:'tickets',
    component:TicketsComponent,
    canActivate:[RoleGuard]
  },{
    path:'updateMovie',
    component:EditMovieComponent,
    canActivate:[RoleGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
