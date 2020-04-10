package com.gaf.reminderserver.dao;

import com.gaf.reminderserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
    Optional<User> findByEmailIgnoreCase(String username);
}
