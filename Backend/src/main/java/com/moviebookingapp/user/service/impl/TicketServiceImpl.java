package com.moviebookingapp.user.service.impl;

import com.moviebookingapp.user.dto.TicketDto;
import com.moviebookingapp.user.dto.TicketsResponseDto;
import com.moviebookingapp.user.entities.Movie;
import com.moviebookingapp.user.entities.MovieId;
import com.moviebookingapp.user.entities.Ticket;
import com.moviebookingapp.user.entities.User;
import com.moviebookingapp.user.exception.InvalidDetailsException;
import com.moviebookingapp.user.exception.ResourceNotFoundException;
import com.moviebookingapp.user.repositories.MovieRepository;
import com.moviebookingapp.user.repositories.TicketRepository;
import com.moviebookingapp.user.repositories.UserRepository;
import com.moviebookingapp.user.service.MovieService;
import com.moviebookingapp.user.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;


    @Override
    public List<Ticket> generateTicketsFromDto(TicketDto ticketDto) {

        User user=userRepository.findByUserName(ticketDto.getUserName()).orElseThrow(
                ()->new ResourceNotFoundException("User","User Name",ticketDto.getUserName())
        );

        MovieId movieId=new MovieId();
        movieId.setMovieName(ticketDto.getMovieName());
        movieId.setTheatreName(ticketDto.getTheatreName());

        Movie movie=movieRepository.findById(movieId).orElseThrow(
                ()->new ResourceNotFoundException("Movie"," movie id",movieId.toString() )
        );
        movie.setNumberOfTicketsBooked(movie.getNumberOfTicketsBooked()+ticketDto.getNumberOfTickets());


        if(ticketDto.getNumberOfTickets()!=ticketDto.getSeatNumbers().size()){
            throw new InvalidDetailsException("Please recheck Seat Numbers and Seats");
        }else{

            Set<String> seatsSelected=new HashSet<>();
            if(movie.getBookedSeats()!=null && !movie.getBookedSeats().isEmpty() && movie.getBookedSeats().length()>0) {
                String[] split = movie.getBookedSeats().split(",");
                for (String s : split) {
                    seatsSelected.add(s);
                }
            }
            for(String s:ticketDto.getSeatNumbers()){
                boolean added=seatsSelected.add(s);
                if(!added){
                    throw new InvalidDetailsException("Oops! Seat"+s+"already booked"+
                            "Please Select other seats");
                }
            }
            String seats=new String();
            for(String s: seatsSelected){
                seats+=s+",";
            }

            movie.setBookedSeats(seats);



            movieRepository.save(movie);
        }


        List<Ticket> tickets=new ArrayList<>();
        for(int i=0;i<ticketDto.getNumberOfTickets();i++){
            Ticket t=new Ticket();
//t.setMovieName(ticket.getMovieName());
//t.setTheatreName(ticket.getTheatreName());
            t.setNumberOfTickets(ticketDto.getNumberOfTickets());
            t.setMovie(movie);
            t.setSeatNumber(ticketDto.getSeatNumbers().get(i));
//t.setUserName(ticket.getUserName());
            t.setUser(user);

            ticketRepository.save(t);

            tickets.add(t);
        }
        return tickets;

    }

    @Override
    public String deleteTickets(String movieName) {

        List<Ticket> tickets=ticketRepository.findByMovie_MovieName(movieName).get();
        //System.out.println(tickets);
        if(tickets==null || tickets.isEmpty()){
            throw new ResourceNotFoundException("Tickets","movie name",movieName);
        }

        for (Ticket t:
                tickets) {
            ticketRepository.delete(t);
        }

        return tickets.toString();

    }

    @Override
    public int numberOfTicketsBookedForMovie(String movieName) {

        List<Movie> movies=movieRepository.findByMovieName(movieName).get();
        if(movies==null || movies.isEmpty()){
            throw new ResourceNotFoundException("Movie"," movie name",movieName);
        }

        List<Ticket> tickets=ticketRepository.findByMovie_MovieName(movieName).get();
        return tickets.size();
    }







//
//    @Override
//    public List<TicketsResponseDto> generateTicketsFromDto(TicketDto ticketDto) {
//
//        User user=userRepository.findByUserName(ticketDto.getUserName()).orElseThrow(
//                ()->new ResourceNotFoundException("User","User Name",ticketDto.getUserName())
//        );
//
//        MovieId movieId=new MovieId();
//        movieId.setMovieName(ticketDto.getMovieName());
//        movieId.setTheatreName(ticketDto.getTheatreName());
//
//        Movie movie=movieRepository.findById(movieId).orElseThrow(
//                ()->new ResourceNotFoundException("Movie"," movie id",movieId.toString() )
//        );
//        movie.setNumberOfTicketsBooked(movie.getNumberOfTicketsBooked()+ticketDto.getNumberOfTickets());
//        movieRepository.save(movie);
//
//        if(ticketDto.getNumberOfTickets()!=ticketDto.getSeatNumbers().size()){
//            throw new InvalidDetailsException("Please recheck Seat Numbers and Seats");
//        }
//
//
//        List<TicketsResponseDto> tickets=new ArrayList<>();
//        for(int i=0;i<ticketDto.getNumberOfTickets();i++){
//            Ticket t=new Ticket();
////t.setMovieName(ticket.getMovieName());
////t.setTheatreName(ticket.getTheatreName());
//            t.setNumberOfTickets(ticketDto.getNumberOfTickets());
//            t.setMovie(movie);
//            t.setSeatNumber(ticketDto.getSeatNumbers().get(i));
////t.setUserName(ticket.getUserName());
//            t.setUser(user);
//
//            ticketRepository.save(t);
//
//            tickets.add(t);
//        }
//        return tickets;
//
//    }

}