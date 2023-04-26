package com.example.displaymybirthday.repo;

import com.example.displaymybirthday.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, Integer> {
    boolean existsByUserName(String userName);

    User findByUserName(String userName);
}
