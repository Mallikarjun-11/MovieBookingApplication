package com.moviebookingapp.user.service;

import com.moviebookingapp.user.dto.TicketDto;
import com.moviebookingapp.user.entities.Ticket;

import java.util.List;

public interface TicketService {



    List<Ticket> generateTicketsFromDto(TicketDto ticketDto);


    String deleteTickets(String movieName);

    int numberOfTicketsBookedForMovie(String movieName);
}
