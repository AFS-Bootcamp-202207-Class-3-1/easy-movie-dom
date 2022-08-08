package com.oocl.easymovie.repository;

import com.oocl.easymovie.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findTop10ByReleaseStatus(int releaseStatus);

    /*@Query(value = "select m from movie m where m.name like ?1 limit ?2,?3",nativeQuery = true)
    Page<Movie> findAllByNameLike(String keyword, int size,int pageSize);*/

    Page<Movie> findByNameContaining(String name, Pageable pageable);

}
