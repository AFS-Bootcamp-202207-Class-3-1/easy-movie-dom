package com.oocl.easymovie.dto;

import com.oocl.easymovie.entity.Movie;
import com.oocl.easymovie.entity.Theater;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TheaterContainMovieResponse {
    private Theater theater;
    List<Movie> onMovieList;
}
