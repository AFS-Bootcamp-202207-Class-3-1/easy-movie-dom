package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.Seating;
import com.oocl.easymovie.repository.SeatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class SeatingServiceTest {
    @InjectMocks
    SeatingService seatingService;

    @Mock
    SeatingRepository seatingRepository;

    @Test
    void should_return_seat_when_find_seat_by_id_given_id() {
        //given
        Seating seating = new Seating();
        doReturn(Optional.of(seating)).when(seatingRepository).findById(seating.getId());

        //when
        Seating seatingReturn = seatingService.findSeatingById(seating.getId());

        //then
        assertEquals(seating, seatingReturn);

    }
}
