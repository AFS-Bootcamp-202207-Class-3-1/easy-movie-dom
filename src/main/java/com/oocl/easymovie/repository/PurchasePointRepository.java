package com.oocl.easymovie.repository;

import com.oocl.easymovie.entity.PurchasePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasePointRepository extends JpaRepository<PurchasePoint, Long> {

    PurchasePoint findByUserId(Long id);
}
