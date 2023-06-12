package com.sarath.location.repository;

import com.sarath.location.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepo extends JpaRepository<Location, Integer>
{
	//use the fields used in Location.java. Location is the  class name
    @Query("select type, count(type) from Location group by type")
    public List<Object[]> findTypeAndTypeCount();
}
