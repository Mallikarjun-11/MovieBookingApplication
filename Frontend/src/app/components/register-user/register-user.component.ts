import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { RegisterUserService } from 'src/app/services/register-user.service';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {


  user:FormGroup;
  

  constructor(private registerService:RegisterUserService) { }

  ngOnInit(): void {
    this.createForm();
  }


  createForm(){
    this.user=new FormGroup({
      firstName:new FormControl(''),
      lastName:new FormControl(''),
      emailId:new FormControl(''),
      userName:new FormControl(''),
      password:new FormControl(''),
      confirmPassword:new FormControl(''),
      contactNumber:new FormControl('')
    })
  }

  onSubmit(){
   
    this.registerService.registerUser(this.user.value).subscribe(
      (response:any)=>{
        console.log(response.userName);       
      },
      error=>console.log(error)
      
    );
  }
}
