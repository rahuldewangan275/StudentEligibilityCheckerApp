package com.example.Student_Eligibility_Checker_App.repo;
import com.example.Student_Eligibility_Checker_App.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {

}
