package com.sarath.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarath.flightreservation.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>
{
    public boolean existsByEmailAndPassword(String email, String password);
    
    User findByEmail(String username);
}
