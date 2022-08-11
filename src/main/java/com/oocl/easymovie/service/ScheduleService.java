package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.Schedule;
import com.oocl.easymovie.entity.Seating;
import com.oocl.easymovie.exception.ScheduleNotFoundException;
import com.oocl.easymovie.exception.SeatingNotFoundException;
import com.oocl.easymovie.repository.ScheduleRepository;
import com.oocl.easymovie.repository.SeatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final SeatingRepository seatingRepository;

    public List<Schedule> findScheduleByTheaterIdAndMovieId(Long theaterId, Long movieId) {
        return scheduleRepository.findAllByTheaterIdAndMovieId(theaterId, movieId);
    }

    public Schedule findById(Long id){
        return scheduleRepository.findById(id).orElseThrow(ScheduleNotFoundException::new);
    }

    public Seating findSeatByScheduleIdWithSeats(Long scheduleId) {
        Schedule schedule=scheduleRepository.findById(scheduleId).orElseThrow(ScheduleNotFoundException::new);
        return seatingRepository.findById(schedule.getSeatingId()).orElseThrow(SeatingNotFoundException::new);
    }
}
