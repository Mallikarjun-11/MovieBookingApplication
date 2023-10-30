import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegisterUserService {
  URL="http://ec2-54-80-208-30.compute-1.amazonaws.com:5000/api/v1/moviebooking"
  constructor(private http:HttpClient) { }

  registerUser(user:any){
    return this.http.post(`${this.URL}/register`,user);
  }

  updateUser(user:any){
  console.log(user);
    
    return this.http.put(`${this.URL}/updateUser`,user);

  }

  forgotPassword(user:any){
    console.log(user);
      
      return this.http.put(`${this.URL}/forgotPassword`,user);
  
    }



}
