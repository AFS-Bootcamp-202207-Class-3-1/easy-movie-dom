package com.oocl.easymovie.controller;

import com.oocl.easymovie.dto.ResultData;
import com.oocl.easymovie.dto.TheaterContainMovieResponse;
import com.oocl.easymovie.entity.Movie;
import com.oocl.easymovie.entity.Theater;
import com.oocl.easymovie.mapper.TheaterMapper;
import com.oocl.easymovie.service.MovieService;
import com.oocl.easymovie.service.TheaterService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    private final TheaterService theaterService;
    private final MovieService movieService;
    private final TheaterMapper theaterMapper;

    public TheaterController(TheaterService theaterService, MovieService movieService, TheaterMapper theaterMapper) {
        this.theaterService = theaterService;
        this.movieService = movieService;
        this.theaterMapper = theaterMapper;
    }


    @GetMapping("/{theaterId}")
    ResultData<TheaterContainMovieResponse> findTheaterById(@PathVariable Long theaterId) {
        Theater theater = theaterService.findTheaterById(theaterId);
        List<Movie> movieList = movieService.findMovieByTheaterId(theaterId);
        return ResultData.success(theaterMapper.toResponse(theater, movieList));
    }

    @GetMapping("/movie/{movieId}")
    ResultData<List<Theater>> findTheaterByMovieId(@PathVariable Long movieId) {
        return ResultData.success(theaterService.findTheaterByMovieId(movieId));
    }
    @GetMapping(params = {"page","pageSize"})
    ResultData<Page<Theater>> findTheaterByPage(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize){
        return ResultData.success(theaterService.findTheaterByPage(page,pageSize));

    }
}
