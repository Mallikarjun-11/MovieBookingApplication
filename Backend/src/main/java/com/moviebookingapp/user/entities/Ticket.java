package com.moviebookingapp.user.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;

//    @Transient
//    private String movieName;
//    @Transient
//    private String theatreName;

    private int numberOfTickets;

//    @JsonIgnore
    private String seatNumber;

//    @Transient
//    private List<String> seatNumbers;
//    @Transient
//    private String userName;

    @ManyToOne(targetEntity=User.class,fetch=FetchType.EAGER)
    private User user;


    @ManyToOne(targetEntity = Movie.class,fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(
                    name = "movie_name",
                    referencedColumnName = "movieName"
            ),
            @JoinColumn(
                name="theatre_name",
                    referencedColumnName = "theatreName"
            )
    })
    private Movie movie;


}
