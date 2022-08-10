package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.Movie;
import com.oocl.easymovie.entity.Schedule;
import com.oocl.easymovie.entity.Theater;
import com.oocl.easymovie.repository.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ScheduleServiceTest {
    @InjectMocks
    ScheduleService scheduleService;

    @Mock
    ScheduleRepository scheduleRepository;

    @Test
    void should_return_schedule_list_when_find_schedule_given_theaterId_and_movieId() {
        //given
        Movie movie = new Movie();
        Theater theater = new Theater();
        Schedule schedule1 = new Schedule();
        Schedule schedule2 = new Schedule();
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        scheduleList.add(schedule1);
        scheduleList.add(schedule2);
        doReturn(scheduleList).when(scheduleRepository).findAllByTheaterIdAndMovieId(theater.getId(), movie.getId());

        //when
        List<Schedule> listOfSchedule = scheduleService.findScheduleByTheaterIdAndMovieId(theater.getId(), movie.getId());

        //then
        assertEquals(scheduleList,listOfSchedule);

    }
}
