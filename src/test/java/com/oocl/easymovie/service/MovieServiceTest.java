package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.DirectorMovieRelation;
import com.oocl.easymovie.entity.Figure;
import com.oocl.easymovie.entity.Movie;
import com.oocl.easymovie.entity.Theater;
import com.oocl.easymovie.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class MovieServiceTest {
    @Mock
    MovieRepository movieRepository;

    @Mock
    TheaterMovieRelationRepository theaterMovieRelationRepository;

    @Mock
    ActorMovieRelationRepository actorMovieRelationRepository;

    @Mock
    DirectorMovieRelationRepository directorMovieRelationRepository;

    @Mock
    CharacterRepository characterRepository;

    @InjectMocks
    MovieService movieService;

    @Test
    void should_return_hot_movie_list_when_get_all_hot_movies_given_null() {
        //given
        Movie movie = new Movie();
        List<Movie> hotMovieList = new ArrayList<Movie>();
        hotMovieList.add(movie);
        doReturn(hotMovieList).when(movieRepository).findTop10ByReleaseStatus(1);

        //when
        List<Movie> hotMovies = movieService.findHotMovie();

        //then
        assertEquals(hotMovieList, hotMovies);

    }

    @Test
    void should_return_upcoming_movies_when_get_all_next_movie_given_null() {
        //given
        Movie movie = new Movie();
        List<Movie> nextMovieList = new ArrayList<Movie>();
        nextMovieList.add(movie);
        doReturn(nextMovieList).when(movieRepository).findTop10ByReleaseStatus(0);

        //when
        List<Movie> nextMovies = movieService.findNextMovie();

        //then
        assertEquals(nextMovieList, nextMovies);

    }

    @Test
    void should_return_movie_in_page_when_get_movie_by_page_and_keyword_given_pageSize_and_keyword_is_not_null() {
        //given
        Movie movie1 = new Movie();
        movie1.setName("testKeyword");
        Movie movie2 = new Movie();
        movie2.setName("testKeyword");
        List<Movie> nextMovieList = new ArrayList<Movie>();
        nextMovieList.add(movie1);
        nextMovieList.add(movie2);
        Page<Movie> moviePage = new PageImpl(nextMovieList);
        doReturn(moviePage).when(movieRepository).findByNameContaining("Keyword", PageRequest.of(0, 2));

        //when
        Page<Movie> movieGotByKeyPage = movieService.findMovieByKeywordAndPage("Keyword", 1, 2);

        //then
        assertEquals(moviePage, movieGotByKeyPage);

    }

    @Test
    void should_return_movie_in_page_when_get_movie_by_page_and_keyword_given_pageSize_and_keyword_is_null() {
        //given
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        List<Movie> nextMovieList = new ArrayList<Movie>();
        nextMovieList.add(movie1);
        nextMovieList.add(movie2);
        Page<Movie> moviePage = new PageImpl(nextMovieList);
        doReturn(moviePage).when(movieRepository).findAll(PageRequest.of(0, 2));

        //when
        Page<Movie> movieGotByKeyPage = movieService.findMovieByKeywordAndPage("", 1, 2);

        //then
        assertEquals(moviePage, movieGotByKeyPage);

    }

    @Test
    void should_return_movie_when_find_movie_by_theater_id_given_theaterId() {
        //given
        Theater theater = new Theater();
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        List<Long> movieId = new ArrayList<Long>();
        movieId.add(movie1.getId());
        movieId.add(movie2.getId());
        List<Movie> movieList = new ArrayList<Movie>();
        movieList.add(movie1);
        movieList.add(movie2);
        doReturn(movieId).when(theaterMovieRelationRepository).findAllByTheaterId(theater.getId());
        doReturn(movieList).when(movieRepository).findAllById(movieId);

        //when
        List<Movie> movieGotByTheaterId = movieService.findMovieByTheaterId(theater.getId());

        //then
        assertEquals(movieList, movieGotByTheaterId);

    }

    @Test
    void should_return_movie_when_find_movie_by_id_given_id() {
        //given
        Movie movie = new Movie();
        doReturn(Optional.of(movie)).when(movieRepository).findById(movie.getId());

        //when
        Movie movieGotById = movieService.findById(movie.getId());

        //then
        assertEquals(movie, movieGotById);
    }

    @Test
    void should_return_figure_when_get_actor_by_movie_id_given_movie_id() {
        //given
        Movie movie = new Movie();
        Figure figure1 = new Figure();
        Figure figure2 = new Figure();
        List<Figure> figureList = new ArrayList<Figure>();
        figureList.add(figure1);
        figureList.add(figure2);
        List<Long> figureIdList = new ArrayList<Long>();
        figureIdList.add(figure1.getId());
        figureIdList.add(figure2.getId());
        doReturn(figureList).when(characterRepository).findAllById(figureIdList);
        doReturn(figureIdList).when(actorMovieRelationRepository).findActorIdByMovieId(movie.getId());

        //when
        List<Figure> actorList = movieService.findActorByMovieId(movie.getId());

        //then
        assertEquals(figureList, actorList);
    }

    @Test
    void should_return_director_when_get_director_by_movie_id_given_movie_id() {
        //given
        Movie movie = new Movie();
        Figure director1 = new Figure();
        Figure director2 = new Figure();
        List<Figure> directorList = new ArrayList<Figure>();
        directorList.add(director1);
        directorList.add(director2);
        List<Long> directorIdList = new ArrayList<Long>();
        directorIdList.add(director1.getId());
        directorIdList.add(director2.getId());
        doReturn(directorList).when(characterRepository).findAllById(directorIdList);
        doReturn(directorIdList).when(directorMovieRelationRepository).findDirectorIdByMovieId(movie.getId());

        //when
        List<Figure> directors = movieService.findDirectorByMovieId(movie.getId());

        //then
        assertEquals(directorList, directors);
    }
}
