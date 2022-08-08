package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.Movie;
import com.oocl.easymovie.repository.MovieRepository;
import com.oocl.easymovie.repository.TheaterMovieRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    public static final int RELEASE_STATUS_HOT = 1;
    public static final int RELEASE_STATUS_NEXT = 0;
    private final MovieRepository movieRepository;
    private final TheaterMovieRelationRepository theaterMovieRelationRepository;
    public List<Movie> findHotMovie() {
        return movieRepository.findTop10ByReleaseStatus(RELEASE_STATUS_HOT);
    }

    public List<Movie> findNextMovie() {
        return movieRepository.findTop10ByReleaseStatus(RELEASE_STATUS_NEXT);
    }

    public Page<Movie> findMovieByKeywordAndPage(String keyword, int page, int pageSize) {
        if (keyword!=null && keyword.length()!=0){
            return movieRepository.findAllByNameLike("%"+keyword+"%",PageRequest.of(page,pageSize));
        }
        return movieRepository.findAll(PageRequest.of(page,pageSize));
    }

    public List<Movie> findMovieByTheaterId(Long theaterId) {
        List<Long> movieIds=theaterMovieRelationRepository.findAllByTheaterId(theaterId);
        return movieRepository.findAllById(movieIds);
    }
}
