package com.oocl.easymovie.mapper;

import com.oocl.easymovie.dto.MovieContainCastResponse;
import com.oocl.easymovie.entity.Figure;
import com.oocl.easymovie.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieMapper {

    public MovieContainCastResponse toResponse(Movie movie, List<Figure> directorList, List<Figure> actorList) {
        MovieContainCastResponse response = new MovieContainCastResponse();
        response.setMovie(movie);
        response.setDirectorList(directorList);
        response.setActorList(actorList);
        return response;
    }
}
