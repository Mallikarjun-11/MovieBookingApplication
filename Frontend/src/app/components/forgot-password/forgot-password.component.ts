import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterUserService } from 'src/app/services/register-user.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  user:any;
  submitted=false;
  updateUserForm!:FormGroup;
  constructor(private formBuilder:FormBuilder, private service:RegisterUserService) { }

  ngOnInit(): void {
    this.updateUserForm=this.formBuilder.group({
      userName:['',Validators.required],
      firstName:['',Validators.required],
      lastName:['',Validators.required],
      emailId:['',Validators.required],
      contactNumber:['',Validators.required],
      password:['',Validators.required],
      confirmPassword:['',Validators.required]
      
    })
  }

  

  
    
  

  onSubmit(){
    this.submitted=true;    
    console.log(this.updateUserForm.value);
    this.user=this.updateUserForm.value;
    this.service.forgotPassword(this.user).subscribe(
      (data)=>{
        console.log(data);
        alert("success");
        
      },
      (error)=>console.log(error)
      
    )


    
  }

}
