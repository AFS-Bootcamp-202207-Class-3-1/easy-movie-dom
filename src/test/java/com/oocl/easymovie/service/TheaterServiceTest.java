package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.Theater;
import com.oocl.easymovie.exception.TheaterNotFoundException;
import com.oocl.easymovie.repository.TheaterMovieRelationRepository;
import com.oocl.easymovie.repository.TheaterRepository;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class TheaterServiceTest {
    @Mock
    TheaterRepository theaterRepository;

    @Mock
    TheaterMovieRelationRepository theaterMovieRelationRepository;

    @InjectMocks
    TheaterService theaterService;

    @Test
    void should_return_theater_when_find_by_id_given_id() {
        //given
        Theater theater = new Theater();
        theater.setId(1L);
        theater.setName("OOCl");
        doReturn(Optional.of(theater)).when(theaterRepository).findById(any());
        //when
        Theater theaterById = theaterService.findTheaterById(1L);
        //then
        assertEquals(theater, theaterById);
    }

    @Test
    void should_return_null_when_find_by_id_given_wrong_id() {
        //given

        //when
        TheaterNotFoundException exception = assertThrows(TheaterNotFoundException.class, () -> theaterService.findTheaterById(999L));
        //then
        assertEquals("Theater not found", exception.getMessage());
    }

    @Test
    void should_return_all_theater_when_find_theater_by_movie_id_given_movie_id() {
        //given
        Theater theater1 = new Theater();
        theater1.setId(1L);
        Theater theater2 = new Theater();
        theater2.setId(2L);
        ArrayList<Theater> arrayList = new ArrayList<>();
        ArrayList<Long> idList = new ArrayList<>();
        idList.add(1L);
        idList.add(2L);
        arrayList.add(theater1);
        arrayList.add(theater2);
        doReturn(idList).when(theaterMovieRelationRepository).findAllByTheaterId(1L);
        doReturn(arrayList).when(theaterRepository).findAllById(idList);
        //when
        List<Theater> theaterByMovieId = theaterService.findTheaterByMovieId(1L);
        //then
        assertEquals(2, theaterByMovieId.size());
    }

    @Test
    void should_return_theater_in_page_when_find_by_page_given_pageSize() {
        //given

        List<Theater> theaterGetByPage=new ArrayList<>();

        Theater theater1 = new Theater();
        theater1.setId(1L);
        theaterGetByPage.add(theater1);

        Theater theater2 = new Theater();
        theater1.setId(2L);
        theaterGetByPage.add(theater2);

        Page<Theater> theaterPage = new PageImpl(theaterGetByPage);

        doReturn(theaterPage).when(theaterRepository).findAll(PageRequest.of(0,2));

        //when
        Page<Theater> theaterByPage= theaterService.findTheaterByPage(1, 2);

        //then
        assertEquals(theaterPage, theaterByPage);
    }

}