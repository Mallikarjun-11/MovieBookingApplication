import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TicketsService {

  private URL="http://ec2-54-80-208-30.compute-1.amazonaws.com:5000/api/v1/moviebooking";

  constructor(private http:HttpClient) { }

  bookTickets(ticketsDto:any){
    return this.http.post(`${this.URL}/bookTicket`,ticketsDto);
  }

  numberOfTicketsBooked(movieName:string){
    return this.http.get(`${this.URL}/ticketsBooked/${movieName}`);
  }

  numberOfTicketsAvailable(movieName:string, theatreName:string){
    return this.http.get(`${this.URL}/tickets/${movieName}/${theatreName}`);
  }
}
