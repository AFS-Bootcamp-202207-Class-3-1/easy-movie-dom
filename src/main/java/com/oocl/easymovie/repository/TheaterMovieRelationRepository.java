package com.oocl.easymovie.repository;

import com.oocl.easymovie.entity.TheaterMovieRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheaterMovieRelationRepository extends JpaRepository<TheaterMovieRelation,Long> {
    List<Long> findAllByTheaterId(Long theaterId);
}
