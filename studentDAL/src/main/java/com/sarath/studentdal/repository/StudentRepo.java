package com.sarath.studentdal.repository;

import com.sarath.studentdal.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student,Long>
{
}
