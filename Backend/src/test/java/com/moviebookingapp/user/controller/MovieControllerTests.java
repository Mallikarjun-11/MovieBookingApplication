package com.moviebookingapp.user.controller;

import com.moviebookingapp.user.controllers.MovieController;
import com.moviebookingapp.user.entities.Movie;
import com.moviebookingapp.user.service.MovieService;

import com.moviebookingapp.user.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(MovieController.class)
@SpringBootTest
@AutoConfigureWebMvc
public class MovieControllerTests {

    @Mock
    private MovieService movieService;

    @Autowired
     MockMvc mockMvc;



    @Test
    void fetchAllMoviesTest() throws Exception {

        Mockito.when(movieService.findAllMovies()).thenReturn(List.of(new Movie(),new Movie()));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/moviebooking/all")
                .accept(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

}
