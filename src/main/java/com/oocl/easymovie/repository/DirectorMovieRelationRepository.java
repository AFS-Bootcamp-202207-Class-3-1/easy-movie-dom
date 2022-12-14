package com.oocl.easymovie.repository;

import com.oocl.easymovie.entity.DirectorMovieRelation;
import com.oocl.easymovie.entity.TheaterMovieRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorMovieRelationRepository extends JpaRepository<DirectorMovieRelation, Long> {
    @Query(value = "select director_id from director_movie_relation where movie_id=?1", nativeQuery = true)
    List<Long> findDirectorIdByMovieId(Long movieId);
}
