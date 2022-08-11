package com.oocl.easymovie.repository;

import com.oocl.easymovie.entity.Schedule;
import com.oocl.easymovie.entity.Seating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatingRepository extends JpaRepository<Seating,Long> {
}
