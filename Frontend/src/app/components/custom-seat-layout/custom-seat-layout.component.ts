import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Movie } from 'src/app/services/movie';
import { TheatresService } from 'src/app/services/theatres.service';
import { Tickets } from 'src/app/services/tickets';
import { TicketsService } from 'src/app/services/tickets.service';


@Component({
  selector: 'app-custom-seat-layout',
  templateUrl: './custom-seat-layout.component.html',
  styleUrls: ['./custom-seat-layout.component.css']
})
export class CustomSeatLayoutComponent implements OnInit {
  movie!:Movie
  bookedSeats:any[]=[];
  confirm:boolean=false;


  totalSeats : any;
  seatArr : any = [];
  ascii : any = 65;
  selectedSeats:any[] =[];

  cartValue!:any;

  // movie:string;
  // theatre:string;
  // numberOfTickets:string;

  bookSeatsDto={    
    movieName:'',
    theatreName:'',
    seatNumbers:[],
    userName:localStorage.getItem('username'),
    numberOfTickets:0
  };
  
  tickets:Tickets[]=[];

  constructor(private route:ActivatedRoute,private ticketService:TicketsService, private theatreService:TheatresService) { }

  ngOnInit(): void {



    this.bookSeatsDto.movieName=this.route.snapshot.paramMap.get('movie');
    this.bookSeatsDto.theatreName=this.route.snapshot.paramMap.get('theatre');

    this.theatreService.getMovie(this.bookSeatsDto.movieName,this.bookSeatsDto.theatreName).subscribe(
      (data)=>{
        console.log(data);
        this.movie=data;  
        this.totalSeats=this.movie.numberOfTickets;
        for(let i=0;i<this.totalSeats/20;i++){
          let arr =[]
          for(let j=1;j<=20;j++){
            arr.push(String.fromCharCode(this.ascii)+j);
          }
          this.ascii++;
          this.seatArr.push(arr);
        }
        // this.bookedSeats=data.bookedSeats.split(",");
        // console.log(this.bookedSeats);
        if (data.bookedSeats) {
          this.bookedSeats = data.bookedSeats.split(",");
        } else {
          this.bookedSeats = []; // Initialize it as an empty array if it's not available.
        }
        console.log(this.totalSeats);
        
        console.log(this.bookedSeats);
        
        

      },
    (error)=>{
      console.log(error);      
    }
    );

    // for(let i=0;i<this.totalSeats/20;i++){
    //   let arr =[]
    //   for(let j=1;j<=20;j++){
    //     arr.push(String.fromCharCode(this.ascii)+j);
    //   }
    //   this.ascii++;
    //   this.seatArr.push(arr);
    // }
  }
  selectSeat(j:any){
    const index = this.selectedSeats.indexOf(j);
    if (index !== -1) {
      this.selectedSeats.splice(index, 1);
    }else{
    this.selectedSeats.push(j);
    }
    console.log(this.selectedSeats);
    
  }
  getSelected(j){
    if(this.selectedSeats.includes(j)){
      return 'selected';
    }else if(this.bookedSeats.includes(j)){
      return "booked";
    }
    return 'not-selected';
  }


  onClick(){
    this.confirm=true;
   this.bookSeatsDto.seatNumbers=this.selectedSeats;   
    console.log(this.bookSeatsDto);        
    this.ticketService.bookTickets(this.bookSeatsDto).subscribe(
      (response:any)=>{
        console.log(response);       
        this.tickets=response;
        console.log("tickets------------------------------------------------");
        this.cartValue=this.bookSeatsDto.numberOfTickets* 200;
        console.log(this.tickets);
        console.log(this.tickets[0].movie.movieName);
        
    },
    error=>{
      console.log(error);      
    });
  }


  // isbooked="no";
  // booked(j){
    
  //   if(this.bookedSeats.includes(j)){
  //     return "booked";
  //   }
  //   return "no";
  // }

}
