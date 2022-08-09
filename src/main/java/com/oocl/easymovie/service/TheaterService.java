package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.Theater;
import com.oocl.easymovie.entity.TheaterMovieRelation;
import com.oocl.easymovie.exception.TheaterNotFoundException;
import com.oocl.easymovie.repository.TheaterMovieRelationRepository;
import com.oocl.easymovie.repository.TheaterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TheaterService {

    private final TheaterRepository theaterRepository;

    private final TheaterMovieRelationRepository theaterMovieRelationRepository;

    public TheaterService(TheaterRepository theaterRepository, TheaterMovieRelationRepository theaterMovieRelationRepository) {
        this.theaterRepository = theaterRepository;
        this.theaterMovieRelationRepository = theaterMovieRelationRepository;
    }

    public Theater findTheaterById(Long theaterId) {
        return theaterRepository.findById(theaterId).orElseThrow(TheaterNotFoundException::new);
    }

    public List<Theater> findTheaterByMovieId(Long movieId) {
        List<Long> theaterId = theaterMovieRelationRepository.findAllByTheaterId(movieId);
        return theaterRepository.findAllById(theaterId);
    }

    public Page<Theater> findTheaterByPage(Integer page, Integer pageSize) {
        if(Objects.isNull(page)) page=1;
        if(Objects.isNull(pageSize)) pageSize=4;
        return theaterRepository.findAll(PageRequest.of(page-1, pageSize));
    }
}
