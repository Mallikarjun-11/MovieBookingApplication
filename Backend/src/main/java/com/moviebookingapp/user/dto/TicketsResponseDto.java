package com.moviebookingapp.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketsResponseDto {

    private Long ticketId;

    private String movieName;

    private String theatreName;

    private List<String> seatNumbers;

    private String userName;

    private int numberOfTickets;
}
