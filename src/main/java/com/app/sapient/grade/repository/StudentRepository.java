package com.app.sapient.grade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.sapient.grade.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
