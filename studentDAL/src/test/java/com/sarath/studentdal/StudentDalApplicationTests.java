package com.sarath.studentdal;

import com.sarath.studentdal.entities.Student;
import com.sarath.studentdal.repository.StudentRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class StudentDalApplicationTests
{
    @Autowired
    private StudentRepo stdRepo;

    @Test
    public void testCreateStudent()
    {
        Student student = new Student();
        student.setName("Sarath");
        student.setCourse("Java");
        student.setFee(200d);
        stdRepo.save(student);
    }

    @Test
    public void testFindStudentByID()
    {
        Student student = stdRepo.findById(2L).orElse(null);
        // https://stackoverflow.com/a/56271859/11962586
        Assert.notNull(student, " No student found under this ID");

        // https://stackoverflow.com/a/7123581/11962586
        // assert student != null;

        System.out.println("Student  : " + student.toString());
    }

    @Test
    public void testUpdateStudentByID()
    {
        Student student = stdRepo.findById(2L).orElse(null);
        assert student != null;
        student.setFee(30d);
        stdRepo.save(student);
    }

    @Test
    public void testDeletStudentByID()
    {
        Student student = new Student();
        student.setId(2L);
        stdRepo.delete(student);
    }

}
