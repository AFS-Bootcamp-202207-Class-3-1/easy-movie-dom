package com.oocl.easymovie.controller;

import com.oocl.easymovie.dto.ResultData;
import com.oocl.easymovie.dto.TheaterContainMovieResponse;
import com.oocl.easymovie.entity.Movie;
import com.oocl.easymovie.entity.Theater;
import com.oocl.easymovie.mapper.TheaterMapper;
import com.oocl.easymovie.service.MovieService;
import com.oocl.easymovie.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
@RequiredArgsConstructor
public class TheaterController {

    private final TheaterService theaterService;
    private final MovieService movieService;
    private final TheaterMapper theaterMapper;


    @GetMapping("/{theaterId}")
    ResultData<TheaterContainMovieResponse> findTheaterById(@PathVariable Long theaterId) {
        Theater theater = theaterService.findTheaterById(theaterId);
        List<Movie> movieList = movieService.findMovieByTheaterId(theaterId);
        return ResultData.success(theaterMapper.toResponse(theater, movieList));
    }
}
