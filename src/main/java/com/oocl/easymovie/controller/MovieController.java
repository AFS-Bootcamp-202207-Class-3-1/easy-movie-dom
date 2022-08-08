package com.oocl.easymovie.controller;

import com.oocl.easymovie.dto.MovieContainCastResponse;
import com.oocl.easymovie.dto.ResultData;
import com.oocl.easymovie.entity.Figure;
import com.oocl.easymovie.entity.Movie;
import com.oocl.easymovie.mapper.MovieMapper;
import com.oocl.easymovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieMapper movieMapper;

    @GetMapping("/hot")
    public ResultData<List<Movie>> findHotMovie() {
        return ResultData.success(movieService.findHotMovie());
    }

    @GetMapping("/next")
    public ResultData<List<Movie>> findNextMovie() {
        return ResultData.success(movieService.findNextMovie());
    }

    @GetMapping(params = {"keyword","page","pageSize"})
    public ResultData<Page<Movie>> findMovieByKeywordAndPage(@RequestParam(required = false,name = "keyword") String keyword, @RequestParam(value = "page") Integer page, @RequestParam(value = "pageSize") Integer pageSize) {
        return ResultData.success(movieService.findMovieByKeywordAndPage(keyword, page, pageSize));
    }

    @GetMapping("/{movieId}")
    public ResultData<MovieContainCastResponse> findMovieContainCastById(@PathVariable Long movieId) {
        Movie movie = movieService.findById(movieId);
        List<Figure> directorList = movieService.findDirectorByMovieId(movieId);
        List<Figure> actorList = movieService.findActorByMovieId(movieId);
        return ResultData.success(movieMapper.toResponse(movie, directorList, actorList));
    }

}
