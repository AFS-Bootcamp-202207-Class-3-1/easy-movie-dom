package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.Schedule;
import com.oocl.easymovie.exception.ScheduleNotFoundException;
import com.oocl.easymovie.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> findScheduleByTheaterIdAndMovieId(Long theaterId, Long movieId) {
        return scheduleRepository.findAllByTheaterIdAndMovieId(theaterId, movieId);
    }

    public Schedule findById(Long id){
        return scheduleRepository.findById(id).orElseThrow(ScheduleNotFoundException::new);
    }
}
