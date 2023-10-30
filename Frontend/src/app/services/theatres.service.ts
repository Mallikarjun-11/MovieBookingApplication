import { HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Theatre } from './theatre';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TheatresService {

  private baseURL = "http://ec2-54-80-208-30.compute-1.amazonaws.com:5000/api/v1/moviebooking"

  constructor(private http:HttpClient) { }

  getTheatres(movieName:string):Observable<Theatre[]>{
    return this.http.get<Theatre[]>(`${this.baseURL}/movies/search/${movieName}`);
  }

  getMovie(movieName:string, theatreName:String):Observable<any>{
    return this.http.get(`${this.baseURL}/getMovie/${movieName}/${theatreName}`);
  }
}
