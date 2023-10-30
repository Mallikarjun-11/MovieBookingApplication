import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import jwtDecode from 'jwt-decode';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials={
    userName:'',
    password:''
  }
  roles='';
  
  constructor(private loginService:LoginService, private router:Router) { }
  
  ngOnInit(): void {

  }
  
  onSubmit(){

    console.log("form is submitted");

    if((this.credentials.userName!='' && this.credentials.password!='' ) && (this.credentials.userName!=null && this.credentials.password!=null)){

      console.log("W");



      this.loginService.generateToken(this.credentials).subscribe(

        (response:any) =>{

          console.log(response);
          
          console.log(response.jwtToken)

          localStorage.setItem('username',this.credentials.userName);

          this.loginService.loginUser(response.jwtToken);   
         
          
          

         window.location.href="/";         
          

        },

        error=>{

          console.log(error);          

        }

      );     
    

    }else{

      console.log("Fields are empty");
    }

   

  }

}
