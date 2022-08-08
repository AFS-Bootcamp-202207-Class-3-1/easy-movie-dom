package com.oocl.easymovie.controller;

import com.oocl.easymovie.dto.ResultData;
import com.oocl.easymovie.entity.Movie;
import com.oocl.easymovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/hot")
    public ResultData<List<Movie>> findHotMovie() {
        return ResultData.success(movieService.findHotMovie());
    }

    @GetMapping("/next")
    public ResultData<List<Movie>> findNextMovie() {
        return ResultData.success(movieService.findNextMovie());
    }

}
