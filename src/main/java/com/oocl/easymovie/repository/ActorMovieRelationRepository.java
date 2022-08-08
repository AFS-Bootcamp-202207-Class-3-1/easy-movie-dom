package com.oocl.easymovie.repository;

import com.oocl.easymovie.entity.ActorMovieRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorMovieRelationRepository extends JpaRepository<ActorMovieRelation, Long> {
    @Query(value = "select actor_id from actor_movie_relation where movie_id=?1", nativeQuery = true)
    List<Long> findAllByActorId(Long theaterId);
}
