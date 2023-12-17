package com.sarath.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarath.flightreservation.entities.User;

public interface UserRepo extends JpaRepository<User, Long>
{
    public boolean existsByEmailAndPassword(String email, String password);
}
