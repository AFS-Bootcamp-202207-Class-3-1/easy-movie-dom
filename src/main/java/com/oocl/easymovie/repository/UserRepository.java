package com.oocl.easymovie.repository;

import com.oocl.easymovie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author edward
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
