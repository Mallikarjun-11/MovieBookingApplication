import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AddMovieService } from 'src/app/services/add-movie.service';

@Component({
  selector: 'app-edit-movie',
  templateUrl: './edit-movie.component.html',
  styleUrls: ['./edit-movie.component.css']
})
export class EditMovieComponent implements OnInit {

  movie:any;
  submitted=false;
  updateMovieForm!:FormGroup;
  constructor(private formBuilder:FormBuilder, private service:AddMovieService) { }

  ngOnInit(): void {
    this.updateMovieForm=this.formBuilder.group({
      movieName:['',Validators.required],
      theatreName:['',Validators.required],
      numberOfTickets:['',Validators.required],
      numberOfTicketsBooked:['',Validators.required],
      ticketStatus:['',Validators.required]
      
    })
  }

  

  
    
  

  onSubmit(){
    this.submitted=true;    
    console.log(this.updateMovieForm.value);
    this.movie=this.updateMovieForm.value;
    this.service.updateMovie(this.movie).subscribe(
      (data)=>{
        console.log(data);
        alert("success");
        
      },
      (error)=>console.log(error)
      
    )


    
  }

  

}

