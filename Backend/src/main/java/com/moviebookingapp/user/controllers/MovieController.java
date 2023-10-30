package com.moviebookingapp.user.controllers;

import com.google.gson.Gson;
import com.moviebookingapp.user.entities.Movie;
import com.moviebookingapp.user.entities.MovieId;
import com.moviebookingapp.user.exception.ErrorDetails;
import com.moviebookingapp.user.exception.ResourceNotFoundException;
import com.moviebookingapp.user.service.MovieService;
import com.moviebookingapp.user.service.RoleService;
import com.moviebookingapp.user.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/v1/moviebooking")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> fetchAllMovies(){
        List<Movie> movies=movieService.findAllMovies();
        return new ResponseEntity<>(movies,HttpStatus.OK);
    }

    @GetMapping("/movies/search/{movieName}")
    public ResponseEntity<List<Movie>> fetchMovieByName(@PathVariable String movieName){
        List<Movie> movies=movieService.findByMovieName(movieName);
        return new ResponseEntity<>(movies,HttpStatus.OK);
    }




    @PostMapping("/add")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        Movie response=movieService.addMovie(movie);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{movieName}/delete")
    @PreAuthorize("hasRole('Admin')")

    public ResponseEntity<String> deleteMovie(@PathVariable String movieName){
        Gson gson = new Gson();
        String response=gson.toJson( movieService.deleteMovie(movieName));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @PutMapping("/update")
    @PreAuthorize("hasRole('Admin')")

    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie){

        Movie response= movieService.updateMovieStatus(movie);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @GetMapping("/getMovie/{movieName}/{theatreName}")
    public ResponseEntity<Movie> getMovie(@PathVariable String movieName,@PathVariable String theatreName){


        Movie response= movieService.findMovie(movieName,theatreName);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/omdb/{movieName}")
    public ResponseEntity<Object> omdbResp(@PathVariable String movieName){
        String url="http://www.omdbapi.com/?t="+movieName+"&apikey=e4e6db82";
        RestTemplate restTemplate=new RestTemplate();
        Object resp=restTemplate.getForObject(url,Object.class);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


    @GetMapping("tickets/{movieName}/{theatreName}")
    public ResponseEntity<Integer> omdbResp(@PathVariable String movieName,@PathVariable String theatreName ){
        Integer resp=movieService.NumberOfTicketsAvailable(movieName,theatreName);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }




}