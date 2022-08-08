package com.oocl.easymovie.repository;

import com.oocl.easymovie.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findTop10ByReleaseStatus(int releaseStatus);

    Page<Movie> findAllByNameLike(String keyword, PageRequest pageRequest);

}
