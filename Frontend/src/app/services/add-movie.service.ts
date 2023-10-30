import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AddMovieService {
  private URL="http://ec2-54-80-208-30.compute-1.amazonaws.com:5000/api/v1/moviebooking"

  constructor(private http:HttpClient) { }

  addMovie(movie){
    return this.http.post(`${this.URL}/add`,movie);
  }

  updateMovie(movie){
    return this.http.put(`${this.URL}/update`,movie);
  }
}
