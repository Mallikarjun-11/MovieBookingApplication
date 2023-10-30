import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TicketsService } from 'src/app/services/tickets.service';

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {

  submitted1=false;
  submitted2=false;
  tickets!:FormGroup;
  movieName!:string;
  movieId!:FormGroup;
  // movieTitle:string;
  // theatreName:string;
  numberOfTicketsBooked:number;
  numberOfTicketsAvailable:number;

  constructor(private formBuilder:FormBuilder, private service:TicketsService) { }

  ngOnInit(): void {
    this.tickets=this.formBuilder.group({     
      movieName:['',Validators.required],
    });

    this.movieId=this.formBuilder.group({   
      movieName:['',Validators.required],  
      theatreName:['',Validators.required],
    });
  }

  onSubmit(){
    console.log(this.tickets.value);
    //console.log(this.tickets.value);
    
    this.service.numberOfTicketsBooked(this.tickets.value.movieName).subscribe(
      (response:any)=>{
        console.log(response);
        this.numberOfTicketsBooked=response;
        this.submitted1=true
      },
      (err)=>console.log(err)
      
    );
  }

  onAvailableSubmit(){
    this.service.numberOfTicketsAvailable(this.movieId.value.movieName,this.movieId.value.theatreName).subscribe(
      (response:any)=>{
        
        this.submitted2=true;
        console.log(response);
        this.numberOfTicketsAvailable=response;        
      },(err)=>console.log(err)
      
    );
  }



}
