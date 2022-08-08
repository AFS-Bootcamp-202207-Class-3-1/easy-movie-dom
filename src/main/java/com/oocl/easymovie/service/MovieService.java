package com.oocl.easymovie.service;

import com.oocl.easymovie.dto.MovieContainCastResponse;
import com.oocl.easymovie.entity.Movie;
import com.oocl.easymovie.exception.MovieNotFoundException;
import com.oocl.easymovie.repository.*;
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
    private final ActorMovieRelationRepository actorMovieRelationRepository;
    private final DirectorMovieRelationRepository directorMovieRelationRepository;
    private final CharacterRepository characterRepository;

    public List<Movie> findHotMovie() {
        return movieRepository.findTop10ByReleaseStatus(RELEASE_STATUS_HOT);
    }

    public List<Movie> findNextMovie() {
        return movieRepository.findTop10ByReleaseStatus(RELEASE_STATUS_NEXT);
    }

    public Page<Movie> findMovieByKeywordAndPage(String keyword, int page, int pageSize) {
        if (keyword != null && keyword.length() != 0) {
            return movieRepository.findAllByNameLike("%" + keyword + "%", PageRequest.of(page, pageSize));
        }
        return movieRepository.findAll(PageRequest.of(page, pageSize));
    }

    public List<Movie> findMovieByTheaterId(Long theaterId) {
        List<Long> movieIds = theaterMovieRelationRepository.findAllByTheaterId(theaterId);
        return movieRepository.findAllById(movieIds);
    }

    public Movie findById(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
    }

    public List<Character> findActorByMovieId(Long movieId) {
        List<Long> actorIdList = actorMovieRelationRepository.findAllByActorId(movieId);
        return characterRepository.findAllById(actorIdList);
    }

    public List<Character> findDirectorByMovieId(Long movieId) {
        List<Long> directorIdList = directorMovieRelationRepository.findAllByDirectorIdId(movieId);
        return characterRepository.findAllById(directorIdList);
    }
}
