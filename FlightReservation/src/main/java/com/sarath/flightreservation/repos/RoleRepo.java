package com.sarath.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarath.flightreservation.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Long>
{

}
