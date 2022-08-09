package com.oocl.easymovie.repository;

import com.oocl.easymovie.entity.Figure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Figure, Long> {
}
