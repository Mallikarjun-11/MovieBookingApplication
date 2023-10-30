import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AddMovieService } from 'src/app/services/add-movie.service';

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.css']
})
export class AddMovieComponent implements OnInit {
  createMovieForm!:FormGroup;
  submitted=false;
  constructor(private formBuilder:FormBuilder, private addMovieService:AddMovieService, private router:Router) { }

  ngOnInit(): void {

    this.createMovieForm=this.formBuilder.group({
      movieName:['',Validators.required],
      theatreName:['',Validators.required],
      numberOfTickets:['',Validators.required]
    })

  }

  onSubmit(){
    this.submitted=true;
    if(this.createMovieForm.invalid){
      return
    }
    this.addMovieService.addMovie(this.createMovieForm.value).subscribe(
      (response)=>{
        console.log(response);
        // window.location.reload()
        this.router.navigate(["/"]);
        
      },
      (error)=>console.log(error)
      
    );
    alert("Success")
  }

}
