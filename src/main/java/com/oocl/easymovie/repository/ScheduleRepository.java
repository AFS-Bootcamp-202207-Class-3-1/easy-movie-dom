package com.oocl.easymovie.repository;

import com.oocl.easymovie.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    List<Schedule> findAllByTheaterIdAndMovieId(Long theaterId,Long movieId);
}
