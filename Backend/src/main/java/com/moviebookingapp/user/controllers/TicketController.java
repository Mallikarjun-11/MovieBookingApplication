package com.moviebookingapp.user.controllers;

import com.google.gson.Gson;
import com.moviebookingapp.user.dto.TicketDto;
import com.moviebookingapp.user.entities.Ticket;
import com.moviebookingapp.user.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/moviebooking")
public class TicketController {
    @Autowired
    private TicketService ticketService;
// @PostMapping("/bookTicket")
// public List<Ticket> bookTickets(@RequestBody Ticket ticket){
// return ticketService.generateTickets(ticket);
// }

    @PostMapping("/bookTicket")
    public ResponseEntity<List<Ticket>> bookTickets(@RequestBody TicketDto ticketDto){
        List<Ticket> tickets=ticketService.generateTicketsFromDto(ticketDto);
        return new ResponseEntity<>(tickets,HttpStatus.OK);
    }

    // @DeleteMapping("/delete/ticket/{movieName}")
// public String deleteTicketByMovieName(@PathVariable String movieName){
// return ticketService.deleteTickets(movieName);
// }
    @DeleteMapping("/delete/ticket/{movieName}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> deleteTicketByMovieName(@PathVariable String movieName){
        ticketService.deleteTickets(movieName);
        return new ResponseEntity<>("Movie "+movieName+"deleted successfully", HttpStatus.OK);
    }


    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/ticketsBooked/{movieName}")
    public ResponseEntity<Integer> numberOfTicketsBooked(@PathVariable String movieName){
        Integer resp=ticketService.numberOfTicketsBookedForMovie(movieName);
        Gson gson=new Gson();
        String res=gson.toJson(resp);
        return new ResponseEntity<>(resp,HttpStatus.OK);
    }












}