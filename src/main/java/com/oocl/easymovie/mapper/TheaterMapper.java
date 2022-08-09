package com.oocl.easymovie.mapper;

import com.oocl.easymovie.dto.TheaterContainMovieResponse;
import com.oocl.easymovie.entity.Movie;
import com.oocl.easymovie.entity.Theater;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TheaterMapper {
    public TheaterContainMovieResponse toResponse(Theater theater, List<Movie> movieList) {
        TheaterContainMovieResponse response = new TheaterContainMovieResponse();
        response.setTheater(theater);
        response.setOnMovieList(movieList);
        return response;
    }
}
