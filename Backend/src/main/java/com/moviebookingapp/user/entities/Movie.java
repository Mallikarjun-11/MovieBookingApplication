package com.moviebookingapp.user.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
@IdClass(MovieId.class)
public class Movie {


    @Id
    private String movieName;
    @Id
    private String theatreName;
//    @EmbeddedId
//    private MovieId movieId;

    private int numberOfTickets;
    private int numberOfTicketsBooked=0;


    private String ticketStatus="Book ASAP";


    private String bookedSeats;


}
