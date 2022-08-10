package com.oocl.easymovie.controller;

import com.oocl.easymovie.dto.ResultData;
import com.oocl.easymovie.entity.Schedule;
import com.oocl.easymovie.entity.Seating;
import com.oocl.easymovie.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping()
    ResultData<List<Schedule>> findScheduleByTheaterIdAndMovieId(@RequestParam Long theaterId, @RequestParam Long movieId) {
        return ResultData.success(scheduleService.findScheduleByTheaterIdAndMovieId(theaterId,movieId));
    }
    @GetMapping("/{scheduleId}/seats")
    ResultData<Seating> findScheduleByScheduleIdWithSeats(@PathVariable(value = "scheduleId")Long  scheduleId){

        return ResultData.success(scheduleService.findSeatByScheduleIdWithSeats(scheduleId));
    }
}
