package com.moviebookingapp.user.service;

import com.moviebookingapp.user.entities.Movie;
import com.moviebookingapp.user.repositories.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.moviebookingapp.user.service.impl.MovieServiceImpl;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class MovieServiceImplTests {
    @Mock
    private MovieRepository repository;

    @InjectMocks
    private MovieServiceImpl service;

    @Test
    void findAllMoviesTest(){
        // Arrange
        when(repository.findAll()).thenReturn(List.of(new Movie(), new Movie()));

        // Act & Assert
        Assertions.assertThat(service.findAllMovies()).hasSize(2);
    }
}
