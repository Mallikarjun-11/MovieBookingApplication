import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SeatsComponent } from './components/seats/seats.component';
import { CustomSeatLayoutComponent } from './components/custom-seat-layout/custom-seat-layout.component';
import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { AuthGuard } from './services/auth.guard';
import { httpInterceptorProviders } from './services/auth.interceptor';
import { TheatresComponent } from './components/theatres/theatres.component';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { AddMovieComponent } from './components/add-movie/add-movie.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { EditMovieComponent } from './components/edit-movie/edit-movie.component';
import { TicketsComponent } from './components/tickets/tickets.component';


@NgModule({
  declarations: [
    AppComponent,
    SeatsComponent,
    CustomSeatLayoutComponent,
    HomeComponent,
    NavbarComponent,
    LoginComponent,
    TheatresComponent,
    RegisterUserComponent,
    AddMovieComponent,
    UpdateUserComponent,
    ForgotPasswordComponent,
    EditMovieComponent,
    TicketsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [AuthGuard, httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
