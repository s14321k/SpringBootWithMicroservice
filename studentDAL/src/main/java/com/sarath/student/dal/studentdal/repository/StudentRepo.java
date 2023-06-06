package com.sarath.student.dal.studentdal.repository;

import com.sarath.student.dal.studentdal.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student,Long>
{
}
