package com.moviebookingapp.user.service;

import com.moviebookingapp.user.entities.Movie;
import com.moviebookingapp.user.entities.MovieId;

import java.util.List;

public interface MovieService {
    List<Movie> findAllMovies();
    List<Movie> findByMovieName(String movieName);
    Movie addMovie(Movie movie);
    String deleteMovie(String movieName);

    Movie updateMovieStatus(Movie movie);

    int NumberOfTicketsAvailable(String movieName, String theatreName);

    Movie findMovie(String movieName, String theatreName);


}
