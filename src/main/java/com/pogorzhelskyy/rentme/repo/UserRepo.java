package com.pogorzhelskyy.rentme.repo;

import com.pogorzhelskyy.rentme.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo  extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
