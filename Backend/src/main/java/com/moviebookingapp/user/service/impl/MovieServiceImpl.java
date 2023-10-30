package com.moviebookingapp.user.service.impl;

import com.moviebookingapp.user.entities.Movie;
import com.moviebookingapp.user.entities.MovieId;
import com.moviebookingapp.user.entities.Ticket;
import com.moviebookingapp.user.exception.InvalidDetailsException;
import com.moviebookingapp.user.exception.ResourceNotFoundException;
import com.moviebookingapp.user.repositories.MovieRepository;
import com.moviebookingapp.user.repositories.TicketRepository;
import com.moviebookingapp.user.service.MovieService;
import com.moviebookingapp.user.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private TicketService ticketService;


    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Movie> findAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        if(movies.isEmpty()){
            throw new ResourceNotFoundException("Movies not found");
        }
        return movies;
    }

    @Override
    public List<Movie> findByMovieName(String movieName) {
//        if(movieName.isEmpty() || movieName.isBlank() || movieName==null){
//            throw new InvalidDetailsException("Movie name cannot be empty");
//        }

        List<Movie> movies=movieRepository.findByMovieName(movieName).get();
        if(movies==null || movies.isEmpty()){
            throw new ResourceNotFoundException("Movies","movie Name",movieName);
        }
        return movies;
    }

    @Override
    public Movie addMovie(Movie movie) {
        Movie createdMovie = movieRepository.save(movie);
        return createdMovie;
    }

    @Override
    public String deleteMovie(String movieName) {
        List<Ticket> tickets = ticketRepository.findByMovie_MovieName(movieName).get();
        if(tickets.size()>0) {
            ticketService.deleteTickets(movieName);
        }
        List<Movie> movies = movieRepository.findByMovieName(movieName).get();
        if(movies==null || movies.isEmpty()) {
            throw new ResourceNotFoundException("Movies","movie Name",movieName);
        }
        for (Movie m :
                movies) {
            movieRepository.delete(m);
        }
        return "Movie " + movieName + " deleted";
    }

    @Override
    public Movie updateMovieStatus(Movie movie) {
        MovieId movieId = new MovieId();
        movieId.setMovieName(movie.getMovieName());
        movieId.setTheatreName(movie.getTheatreName());

        Movie movieToBeUpdated = movieRepository.findById(movieId).orElseThrow(
                ()-> new ResourceNotFoundException("Movie","Movie Name, Theatre Name",movie.getMovieName()+", "+movie.getTheatreName())
        );
        movieToBeUpdated.setMovieName(movie.getMovieName());
        movieToBeUpdated.setTheatreName(movie.getTheatreName());
        movieToBeUpdated.setNumberOfTickets(movie.getNumberOfTickets());
        movieToBeUpdated.setNumberOfTicketsBooked(movie.getNumberOfTicketsBooked());
        movieToBeUpdated.setTicketStatus(movie.getTicketStatus());

        Movie updatedMovie = movieRepository.save(movieToBeUpdated);
        return updatedMovie;
    }

    @Override
    public int NumberOfTicketsAvailable(String movieName,String theatreName) {
        MovieId movieId=new MovieId(movieName,theatreName);
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                ()-> new ResourceNotFoundException("Movie","Movie Name, Theatre Name",movieId.getMovieName()+", "+movieId.getTheatreName())
        );
        int numberOfTicketsAvailable = movie.getNumberOfTickets() - movie.getNumberOfTicketsBooked();
        return numberOfTicketsAvailable;
    }

    @Override
    public Movie findMovie(String movieName, String theatreName){

        MovieId movieId = new MovieId();
        movieId.setMovieName(movieName);
        movieId.setTheatreName(theatreName);
        System.out.println("--------------------------------------------");
        System.out.println(movieId.toString());
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                ()-> new ResourceNotFoundException("Movie","Movie Name, Theatre Name",movieId.getMovieName()+", "+movieId.getTheatreName())
        );
        return movie;
    }
}