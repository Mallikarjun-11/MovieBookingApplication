import { HttpClient } from '@angular/common/http';

import { Injectable } from '@angular/core';
import { Observable, catchError, map, of } from 'rxjs';

import jwtDecode from 'jwt-decode';

 

@Injectable({

  providedIn: 'root'

})

export class LoginService {

  // private URL="http://localhost:8080/api/v1/moviebooking";
  private URL="http://ec2-54-80-208-30.compute-1.amazonaws.com:5000/api/v1/moviebooking";
   

  constructor(private http:HttpClient) { }

 

  generateToken(credentials:any){

    return this.http.post(`${this.URL}/login`,credentials);

  }

 
  // isAdmin:boolean=false;
  getRoles(userName:any){

    return 

  }

 

  isAdmin(userName:string): Observable<boolean> {

    return this.http.get(`${this.URL}/userRole/${userName}`).pipe(
      map((response: any) => {
        console.log(response);
        console.log(userName);
        console.log(response.roles.includes('Admin'));
        return response.roles.includes('Admin');
      }),
      catchError((error) => {
        console.log(error);
        return of(false); // Return false in case of an error.
      })
    );
  }

 

 

 

  loginUser(token:string){

    localStorage.setItem("token",token);

    return true;

  }

 

  isLoggedIn(){

    let token=localStorage.getItem("token");

    if(token==undefined || token=='' || token==null){

      return false;

    }else{

      return true;

    }

  }

 

  logout(){

    localStorage.removeItem('token');
    return true;

  }

 

  getToken(){

    return localStorage.getItem('token');

  }

  isExpired(){
    let jwt:any=jwtDecode(localStorage.getItem('token'));
    let expTime=jwt.exp;
    let loginTime=jwt.iat;
    
    console.log(expTime-loginTime);
    

    // setInterval(()=>{
    //   if(expTime-loginTime)
    // })
  }
  

  
}

 