import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { RegisterUserService } from 'src/app/services/register-user.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {
  getUser=localStorage.getItem('username');
  user:any;
  submitted=false;
  updateUserForm!:FormGroup;
  constructor(private formBuilder:FormBuilder, private service:RegisterUserService) { }

  ngOnInit(): void {
    this.updateUserForm=this.formBuilder.group({
      userName:[{ value: this.getUser, disabled: false }],
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
    this.service.updateUser(this.user).subscribe(
      (data)=>{
        console.log(data);
        alert("success");
        
      },
      (error)=>console.log(error)
      
    )


    
  }

  

}
