package com.moviebookingapp.user.repositories;

import com.moviebookingapp.user.entities.Movie;
import com.moviebookingapp.user.entities.MovieId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, MovieId> {
//public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<List<Movie>> findByMovieName(String movieName);
}
