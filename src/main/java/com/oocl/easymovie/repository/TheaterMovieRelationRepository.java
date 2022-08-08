package com.oocl.easymovie.repository;

import com.oocl.easymovie.entity.TheaterMovieRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterMovieRelationRepository extends JpaRepository<TheaterMovieRelation, Long> {
    @Query(value = "select movie_id from theater_movie_relation where theater_id=?1", nativeQuery = true)
    List<Long> findAllByTheaterId(Long theaterId);
}
