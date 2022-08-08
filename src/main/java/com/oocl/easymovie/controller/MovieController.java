package com.oocl.easymovie.controller;

import com.oocl.easymovie.dto.MovieContainCastResponse;
import com.oocl.easymovie.dto.ResultData;
import com.oocl.easymovie.entity.Movie;
import com.oocl.easymovie.mapper.MovieMapper;
import com.oocl.easymovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @GetMapping("/hot")
    public ResultData<List<Movie>> findHotMovie() {
        return ResultData.success(movieService.findHotMovie());
    }

    @GetMapping("/next")
    public ResultData<List<Movie>> findNextMovie() {
        return ResultData.success(movieService.findNextMovie());
    }

    @GetMapping("/{keyword}/{page}/{pageSize}")
    public ResultData<Page<Movie>> findMovieByKeywordAndPage(@PathVariable String keyword, @PathVariable int page, @PathVariable int pageSize) {
        return ResultData.success(movieService.findMovieByKeywordAndPage(keyword, page, pageSize));
    }

    @GetMapping("/{movieId}")
    public ResultData<MovieContainCastResponse> findMovieContainCastById(@PathVariable Long movieId) {
        Movie movie = movieService.findById(movieId);
        List<Character> directorList = movieService.findDirectorByMovieId(movieId);
        List<Character> actorList = movieService.findActorByMovieId(movieId);
        return ResultData.success(movieMapper.toResponse(movie, directorList, actorList));
    }

}
