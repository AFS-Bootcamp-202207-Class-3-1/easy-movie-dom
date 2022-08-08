package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.Figure;
import com.oocl.easymovie.entity.Movie;
import com.oocl.easymovie.exception.MovieNotFoundException;
import com.oocl.easymovie.repository.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    public static final int RELEASE_STATUS_HOT = 1;
    public static final int RELEASE_STATUS_NEXT = 0;
    private final MovieRepository movieRepository;
    private final TheaterMovieRelationRepository theaterMovieRelationRepository;
    private final ActorMovieRelationRepository actorMovieRelationRepository;
    private final DirectorMovieRelationRepository directorMovieRelationRepository;
    private final CharacterRepository characterRepository;

    public MovieService(MovieRepository movieRepository, TheaterMovieRelationRepository theaterMovieRelationRepository, ActorMovieRelationRepository actorMovieRelationRepository, DirectorMovieRelationRepository directorMovieRelationRepository, CharacterRepository characterRepository) {
        this.movieRepository = movieRepository;
        this.theaterMovieRelationRepository = theaterMovieRelationRepository;
        this.actorMovieRelationRepository = actorMovieRelationRepository;
        this.directorMovieRelationRepository = directorMovieRelationRepository;
        this.characterRepository = characterRepository;
    }

    public List<Movie> findHotMovie() {
        return movieRepository.findTop10ByReleaseStatus(RELEASE_STATUS_HOT);
    }

    public List<Movie> findNextMovie() {
        return movieRepository.findTop10ByReleaseStatus(RELEASE_STATUS_NEXT);
    }

    public Page<Movie> findMovieByKeywordAndPage(String keyword, int page, int pageSize) {
        if (keyword != null && keyword.length() != 0) {
            return movieRepository.findByNameContaining(keyword, PageRequest.of(page-1, pageSize));
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

    public List<Figure> findActorByMovieId(Long movieId) {
        List<Long> actorIdList = actorMovieRelationRepository.findAllByActorId(movieId);
        return characterRepository.findAllById(actorIdList);
    }

    public List<Figure> findDirectorByMovieId(Long movieId) {
        List<Long> directorIdList = directorMovieRelationRepository.findAllByDirectorIdId(movieId);
        return characterRepository.findAllById(directorIdList);
    }
}
