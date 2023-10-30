package com.moviebookingapp.user.repositories;

import com.moviebookingapp.user.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,String> {

    Optional<List<Ticket>> findByMovie_MovieName(String movieName);

}
