package com.sarath.location.repository;

import com.sarath.location.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<Location, Integer>
{
}
