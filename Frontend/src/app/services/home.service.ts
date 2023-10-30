import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Movie } from './movie';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  //private baseURL = "http://localhost:8080/api/v1/moviebooking";
  private baseURL = "http://ec2-54-80-208-30.compute-1.amazonaws.com:5000/api/v1/moviebooking";
  
  constructor(private httpClient: HttpClient) { }
  
  getMoviesList(): Observable<Movie[]>{
    return this.httpClient.get<Movie[]>(`${this.baseURL}/all`);
  }

  getOmdbResponse(movieName:string):any{
    return this.httpClient.get(`${this.baseURL}/omdb/${movieName}`);
  }

  deleteMovie(movieName:string):Observable<any>{
    return this.httpClient.delete(`${this.baseURL}/${movieName}/delete`);
  }
}
