package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.Movie;
import com.oocl.easymovie.entity.Schedule;
import com.oocl.easymovie.entity.Seating;
import com.oocl.easymovie.entity.Theater;
import com.oocl.easymovie.repository.ScheduleRepository;
import com.oocl.easymovie.repository.SeatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ScheduleServiceTest {
    @InjectMocks
    ScheduleService scheduleService;

    @Mock
    ScheduleRepository scheduleRepository;

    @Mock
    SeatingRepository seatingRepository;

    @Mock
    SeatingService seatingService;

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
        assertEquals(scheduleList, listOfSchedule);

    }

    @Test
    void should_return_schedule_when_get_schedule_by_id_given_available_id() {
        //given
        Schedule schedule = new Schedule();
        doReturn(Optional.of(schedule)).when(scheduleRepository).findById(schedule.getId());

        //when
        Schedule gotSchedule = scheduleService.findById(schedule.getId());

        //then
        assertEquals(schedule, gotSchedule);

    }

    @Test
    void should_throw_exception_when_get_schedule_by_id_given_wrong_id() {
        //given
        Schedule schedule = new Schedule();

        //when
        Exception expectedException = null;
        try {
            scheduleService.findById(schedule.getId());
        } catch (Exception e) {
            expectedException = e;
        }

        //then
        assert expectedException != null;
        assertEquals(expectedException.getMessage(), "schedule no found");
    }

    @Test
    void should_return_seat_when_find_seat_by_scheduleId_given_correct_scheduleId() {
        //given
        Seating seating = new Seating();
        seating.setId((long) 1);
        Schedule schedule = new Schedule();
        schedule.setId((long) 2);
        schedule.setSeatingId(seating.getId());
        doReturn(Optional.of(schedule)).when(scheduleRepository).findById(schedule.getId());
        doReturn(Optional.of(seating)).when(seatingRepository).findById(schedule.getSeatingId());

        //when
        Seating seat = scheduleService.findSeatByScheduleIdWithSeats(schedule.getId());

        //then
        assertEquals(seating, seat);

    }

    @Test
    void should_throw_exception_when_find_seat_by_scheduleId_given_wrong_scheduleId() {
        //given
        Seating seating = new Seating();
        seating.setId((long) 1);
        Schedule schedule = new Schedule();
        schedule.setId((long) 2);
        schedule.setSeatingId(seating.getId());
        doReturn(Optional.of(seating)).when(seatingRepository).findById(schedule.getSeatingId());

        //when
        Exception expectedException = null;
        try {
            scheduleService.findSeatByScheduleIdWithSeats(schedule.getId());
        } catch (Exception e) {
            expectedException = e;
        }

        //then
        assert expectedException != null;
        assertEquals(expectedException.getMessage(), "schedule no found");

    }

    @Test
    void should_throw_exception_when_find_seat_by_scheduleId_given_true_scheduleId_but_no_seatingId() {
        //given
        Seating seating = new Seating();
        seating.setId((long) 1);
        Schedule schedule = new Schedule();
        schedule.setId((long) 2);
        schedule.setSeatingId(seating.getId());
        doReturn(Optional.of(schedule)).when(scheduleRepository).findById(schedule.getId());

        //when
        Exception expectedException = null;
        try {
            scheduleService.findSeatByScheduleIdWithSeats(schedule.getId());
        } catch (Exception e) {
            expectedException = e;
        }

        //then
        assert expectedException != null;
        assertEquals(expectedException.getMessage(), "Seating Not found");

    }
}
