package com.moviebookingapp.user.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@Embeddable
public class MovieId implements Serializable {
    private String movieName;
    private String theatreName;

}
