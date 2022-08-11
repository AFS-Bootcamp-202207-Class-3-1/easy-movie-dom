package com.oocl.easymovie.repository;

import com.oocl.easymovie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author edward
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from `user` o where o.username=?1", nativeQuery = true)
    User findOneByUsername(String username);
}
