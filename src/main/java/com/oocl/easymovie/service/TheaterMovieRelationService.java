package com.oocl.easymovie.service;

import com.oocl.easymovie.repository.TheaterMovieRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterMovieRelationService {
private final TheaterMovieRelationRepository theaterMovieRelationRepository;
    public List<Long> findMoviesIdByTheaterId(Long theaterId) {
        return theaterMovieRelationRepository.findAllByTheaterId(theaterId);
    }
}
