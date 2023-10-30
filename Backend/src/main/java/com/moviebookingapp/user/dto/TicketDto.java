package com.moviebookingapp.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {


    private String movieName;

    private String theatreName;


    private List<String> seatNumbers;

    private String userName;

    private int numberOfTickets;
}
